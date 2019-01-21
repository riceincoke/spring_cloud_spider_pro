package spider.myspider.redisComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spider.spiderCore.idbcore.IDbManager;
import spider.spiderCore.idbcore.IDbWritor;
import spider.spiderCore.idbcore.IGenerator;

/**
 * @author 一杯咖啡
 * @desc
 * @createTime 2019-01-04-13:13
 */
@Component
public class DefaultDataUtil {
    private IGenerator iGenerator;
    private IDbWritor iDbWritor;
    private IDbManager iDbManager;

    public DefaultDataUtil() {
    }

    @Autowired
    public DefaultDataUtil(IGenerator iGenerator, IDbWritor iDbWritor, IDbManager iDbManager) {
        this.iGenerator = iGenerator;
        this.iDbWritor = iDbWritor;
        this.iDbManager = iDbManager;
    }

    @Override
    public String toString() {
        return "DefaultRedisDataBase{" +
                "iGenerator=" + iGenerator +
                ", iDbWritor=" + iDbWritor +
                ", iDbManager=" + iDbManager +
                '}';
    }

    public IGenerator getiGenerator() {
        return iGenerator;
    }

    public void setiGenerator(IGenerator iGenerator) {
        this.iGenerator = iGenerator;
    }

    public IDbWritor getiDbWritor() {
        return iDbWritor;
    }

    public void setiDbWritor(IDbWritor iDbWritor) {
        this.iDbWritor = iDbWritor;
    }

    public IDbManager getiDbManager() {
        return iDbManager;
    }

    public void setiDbManager(IDbManager iDbManager) {
        this.iDbManager = iDbManager;
    }
}