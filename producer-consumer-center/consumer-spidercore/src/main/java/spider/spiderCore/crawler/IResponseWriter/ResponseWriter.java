package spider.spiderCore.crawler.IResponseWriter;

import spider.spiderCore.entities.Page;

/**
 * @author 一杯咖啡
 * @desc
 * @createTime 2018-11-30-13:12
 */
public interface ResponseWriter {
    boolean writerResponsePageToRedis(Page page);
}
