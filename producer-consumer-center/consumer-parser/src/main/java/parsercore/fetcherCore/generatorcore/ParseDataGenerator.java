package parsercore.fetcherCore.generatorcore;

import commoncore.customUtils.SerializeUtil;
import commoncore.entity.httpEntity.ResponseData;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


/**
 * @author Twilight
 * @desc
 * @createTime 2019-01-15-15:17
 */
@Component
public class ParseDataGenerator implements IParseDataGenerator<ResponseData> {
    private static final Logger log = Logger.getLogger(ParseDataGenerator.class);
    @Autowired
    private IResponseDataFilter iResponseDataFilter;
    @Value(value = "${my.responseList.redisKey}")
    private String dataKey;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResponseData getData() {
        log.debug("从数据库提取数据.......");
        ResponseData responseData = null;

        String responseDataStr = (String) redisTemplate.opsForList().leftPop(dataKey);
        if (!StringUtils.isBlank(responseDataStr)) {
            try {
                responseData = (ResponseData) SerializeUtil.deserializeToObject(responseDataStr);
            } catch (Exception e) {
                log.error("反序列化异常" + e.getMessage());
            }
            if (iResponseDataFilter != null) {
                boolean passOrNot = iResponseDataFilter.pass(responseData);
                if (passOrNot) {
                    log.info("TIP:这里可能有问题");
                    this.getData();
                }
            }
        } else {
            log.warn("redis 中无后续数据");
        }

        //模拟数据提取
        /*responseData = new ResponseData("dayuwang",
                new CrawlDatum("http://cq.qq.com/a/20190115/005822.htm"),
                33, "text/html",
                "hello".getBytes());*/
        return responseData;
    }
}