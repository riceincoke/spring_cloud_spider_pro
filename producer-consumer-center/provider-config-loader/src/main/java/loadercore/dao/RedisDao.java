package loadercore.dao;

import commoncore.customUtils.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * <p>项目名称: ${小型分布式爬虫} </p>
 * <p>描述: [redis 数据操作 ] </p>
 * <p>创建时间: ${date} </p>
 *
 * @author <a href="mail to: 1139835238@qq.com" rel="nofollow">whitenoise</a>
 **/
@Component
public class RedisDao {
    @Autowired
    RedisTemplate redisTemplate;
    private SerializeUtil serializeUtil = new SerializeUtil();

    /**
     * @Title：${enclosing_method}
     * @Description: [redis 插入序列化的对象]
     */
    public boolean insertAObject(String keyString, Object obj) {
        String objStr = null;
        try {
            objStr = SerializeUtil.serializeToString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Long reindex = redisTemplate.opsForSet().add(keyString, objStr);
        return reindex != 0;
    }
}
