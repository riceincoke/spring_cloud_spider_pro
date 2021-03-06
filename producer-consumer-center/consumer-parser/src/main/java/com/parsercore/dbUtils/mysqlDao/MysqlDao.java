package com.parsercore.dbUtils.mysqlDao;


import commoncore.entity.loadEntity.DomainRule;
import commoncore.entity.loadEntity.MyNew;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Twilight
 */
@Component
public class MysqlDao implements IMysqlDao<MyNew, DomainRule> {
    private static final Logger LOG = LoggerFactory.getLogger(MysqlDao.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MysqlDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * desc:初始化mysql建表
     **/
    public void creatTable(String tableName) {
        try {
            jdbcTemplate.execute(
                    "CREATE TABLE IF NOT EXISTS " + tableName
                            + "(new_id int(11) NOT NULL AUTO_INCREMENT,"
                            + "title varchar(100)," +
                            "url varchar(200)," +
                            "content longtext," +
                            "time VARCHAR(50)," +
                            "media VARCHAR(20)," +
                            "author VARCHAR(20),"
                            + "PRIMARY KEY (new_id)"
                            + ") ENGINE=InnoDB DEFAULT CHARSET=utf8;"
            );
            LOG.info("Tips : CreatTable - " + tableName + " Success");
        } catch (Exception e) {
            LOG.error("mysql未开启或JDBCHelper.createMysqlTemplate中参数配置不正确! " + e.getCause());
        }
    }

    /**
     * @Desc 持久化数据到mysql数据库
     */
    @Override
    public void insertNew(MyNew myNew) {
        String sql = "insert into " + " dayuwang " + "(title,url,content,time,media,author) values(?,?,?,?,?,?)";
        //title url content time media author
        int x = jdbcTemplate.update(sql, myNew.getTitle(), myNew.getURL(), myNew.getContent(), myNew.getTime(), myNew.getMedia(), myNew.getAuthor());
        if (x != 0) {
            LOG.info("存入数据成功");
        } else {
            LOG.info("存入数据失败");
        }
    }

    /**
     * desc:获取mysql 中的解析器配置
     **/
    @Override
    public DomainRule getDomainRuleFromMysql(String siteName) {
        String sql = "select * from domain_rule where site_name = ?";
        try {
            DomainRule domainRule = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                DomainRule domainRule1 = new DomainRule();
                domainRule1.setDomainId(rs.getInt(1));
                domainRule1.setAuthor_rule(rs.getString(2));
                domainRule1.setContent_rule(rs.getString(3));
                domainRule1.setMedia_rule(rs.getString(4));
                domainRule1.setSiteName(siteName);
                domainRule1.setTime_rule(rs.getString(6));
                domainRule1.setTitle_rule(rs.getString(7));
                return domainRule1;
            }, siteName);
            return domainRule;
        } catch (Exception e) {
            LOG.error("未能查询到 " + siteName + " 对应的记录");
            LOG.error(e.getCause() + " : " + e.getMessage());
            return null;
        }
    }
}
