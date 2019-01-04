package spider.myspider.redisSpider;

import org.springframework.stereotype.Component;
import spider.spiderCore.crawldb.IDataBase;

/**
 * @author 一杯咖啡
 */
@Component
public class RedisDb implements IDataBase<String> {

    private String seedsList = "seeds";
    private String undoneList = "undone";
    private String lindList = "done";
    private String redirectList = "redirect";

    @Override
    public String getCrawlDB() {
        return seedsList;
    }

    @Override
    public String getFetchDB() {
        return undoneList;
    }

    @Override
    public String getLinkDB() {
        return lindList;
    }

    @Override
    public String getRedirectDB() {
        return redirectList;
    }

    /**
     * desc: 清空数据库
     **/
    @Override
    public void clear() {
    }
}
