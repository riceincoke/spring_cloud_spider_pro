package spider.spiderCore.iexecutorCom;

/**
 * @author Twilight
 * @desc
 * @createTime 2019-01-07-15:23
 */
public interface ISimpleParse<T, D> {
    T parseLinks(D dataObj);
}
