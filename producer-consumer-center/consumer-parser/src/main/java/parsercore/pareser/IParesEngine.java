package parsercore.pareser;


import commoncore.entity.responseEntity.ResponseData;

/**
 * @author 一杯咖啡
 * @desc 解析器启动接口
 * @createTime 2018-12-21-15:51
 */
public interface IParesEngine {
    /**
     * desc:默认解析
     **/
    void parseRun();
    /**
     * desc:解析传入的数据
     * @param responseData
     **/
    void parseRun(ResponseData responseData);
}