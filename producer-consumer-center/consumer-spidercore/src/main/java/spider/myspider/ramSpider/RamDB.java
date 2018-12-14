package spider.myspider.ramSpider;

import org.springframework.stereotype.Component;
import spider.spiderCore.crawldb.Idbutil.DataBase;
import spider.spiderCore.entities.CrawlDatum;

import java.util.HashMap;


/**
 * @author hu
 */
@Component
public class RamDB implements DataBase<HashMap>{
    
    private HashMap<String, CrawlDatum> crawlDB = new HashMap<String, CrawlDatum>();
    private HashMap<String, CrawlDatum> fetchDB = new HashMap<String, CrawlDatum>();
    private HashMap<String, CrawlDatum> linkDB = new HashMap<String, CrawlDatum>();
    private HashMap<String, String> redirectDB = new HashMap<String, String>();

    @Override
    public HashMap<String, CrawlDatum> getCrawlDB() {
        return crawlDB;
    }

    @Override
    public HashMap<String, CrawlDatum> getFetchDB() {
        return fetchDB;
    }

    @Override
    public HashMap<String, CrawlDatum> getLinkDB() {
        return linkDB;
    }

    @Override
    public HashMap<String, String> getRedirectDB() {
        return redirectDB;
    }

    @Override
    public void clear() {
        this.crawlDB.clear();
        this.fetchDB.clear();
        this.linkDB.clear();
        this.redirectDB.clear();
    }
}
