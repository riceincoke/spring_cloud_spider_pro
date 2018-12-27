package commoncore.entity.responseEntity;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author 一杯咖啡
 * @desc
 * @createTime 2018-12-24-15:24
 */
public class ResponseData implements Serializable {
    private static final long serialVersionUID= 1L;
    private String siteName;
    private CrawlDatum datum;
    private Integer code;
    private String contentType;
    private byte[] content;

    @Override
    public String toString() {
        return "ResponseData{" +
                "siteName='" + siteName + '\'' +
                ", datum=" + datum +
                ", code=" + code +
                ", contentType='" + contentType + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }

    public ResponseData(String siteName, CrawlDatum datum, Integer code, String contentType, byte[] content) {
        this.siteName = siteName;
        this.datum = datum;
        this.code = code;
        this.contentType = contentType;
        this.content = content;
    }


    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public CrawlDatum getDatum() {
        return datum;
    }

    public void setDatum(CrawlDatum datum) {
        this.datum = datum;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}