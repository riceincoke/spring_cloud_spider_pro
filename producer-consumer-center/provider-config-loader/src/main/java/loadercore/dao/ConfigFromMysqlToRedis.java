
package loadercore.dao;

import commoncore.customUtils.SerializeUtil;
import commoncore.entity.configEntity.SiteConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * <p>项目名称: ${小型分布式爬虫} </p>
 * <p>文件名称: ${MysqlToRedis} </p>
 * <p>描述: [读取mysql 网站配置信息到redis队列] </p>
 *
 * @author 一杯咖啡
 */

@Component
public class ConfigFromMysqlToRedis {

    private static final Logger LOG = Logger.getLogger(ConfigFromMysqlToRedis.class);
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SitesConfigDao sitesConfigDao;
    private SerializeUtil serializeUtil = new SerializeUtil();

    /**
     * desc:主节点需要该功能从mysql数据库读到redis队列
     **/
    public void mysqlWriteRedis() {
        List<SiteConfig> scs = sitesConfigDao.Read();
        String str;
        for (SiteConfig x : scs) {
            try {
                str = SerializeUtil.serializeToString(x);
                for (int i = 0; i < 3; i++) {
                    redisTemplate.opsForList().leftPush("sites", str);
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                LOG.error("序列化SiteConfig对象出错");
            }
        }
    }

    /**
     * desc:从redis中读取配置
     **/
    public void readRedis() {
        List<String> sites = redisTemplate.opsForList().range("sites", 0, -1);
        if (sites.size() > 0) {
            for (String site : sites) {
                try {
                    SiteConfig siteConfig = (SiteConfig) SerializeUtil.deserializeToObject(site);
                    System.out.println("siteconfig:" + siteConfig.toString());
                } catch (Exception e) {
                    LOG.error("反序列化SiteConfig对象出错");
                }
            }
        } else {
            LOG.error("未取得redis中的值");
        }
    }
}

