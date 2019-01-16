package spider;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author 一杯咖啡
 * @desc 爬虫启动类 （springboot 启动后，启动爬虫；检查环境）
 * @createTime ${YEAR}-${MONTH}-${DAY}-${TIME}
 */
@SpringBootApplication
@ComponentScan(basePackages = {"spider", "commoncore"})
@EnableEurekaClient
public class SpiderApplication {
    private static final Logger LOG = Logger.getLogger(SpiderApplication.class);
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private DataSource dataSource;

    /**
     * desc: 初始化配置监视，出异常直接退出
     **/
    @PostConstruct
    public void monitor() {
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        LOG.info("环境监视：\n");
        //获取redis连接，连接失败则退出程序
        try {
            //  redisTemplate.getConnectionFactory().getConnection();
        } catch (Exception e) {
            LOG.error("redis 连接失败");
            System.exit(0);
        }
        try {
            dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error("数据库连接失败");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(SpiderApplication.class, args);
    }

    /* *//**
     * desc:线程池
     *
     * @Return:Executor
     **//*
    @Bean(name = "executor")
    public ThreadPoolTaskExecutor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.initialize();
        executor.setAllowCoreThreadTimeOut(true);
        executor.setKeepAliveSeconds(2);
        executor.setQueueCapacity(10);
        return executor;

    }*/
}
