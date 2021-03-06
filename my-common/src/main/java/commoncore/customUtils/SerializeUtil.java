package commoncore.customUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * desc: 对象序列化和反序列化工具
 *
 * @author 一杯咖啡
 */
public class SerializeUtil {
    private static final Logger LOG = LoggerFactory.getLogger(SerializeUtil.class);

    /**
     * @Title：${序列化}
     * @Description: [序列化对象为字符串]
     * @author <a href="mail to: 113985238@qq.com" rel="nofollow">whitenoise</a>
     */
    public static Optional<String> serializeToString(Object obj) {
        LOG.warn("对象序列化");
        try {
            ObjectOutputStream objOut;
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            objOut = new ObjectOutputStream(byteOut);
            objOut.writeObject(obj);
            //此处只能是ISO-8859-1,但是不会影响中文使用
            return Optional.ofNullable(byteOut.toString("ISO_8859_1"));
        } catch (IOException e) {
            LOG.error("对象序列化错误");
            return Optional.empty();
        }
    }

    /**
     * @Title：${反序列化}
     * @Description: [反序列化字符串为对象]
     * @author <a href="mail to: *******@******.com" rel="nofollow">作者</a>
     */
    public static <T> Optional<T> deserializeToObject(String str) {
        LOG.warn("对象反序列化，进入程序");
        try {
            ByteArrayInputStream byteIn = new ByteArrayInputStream(str.getBytes(StandardCharsets.ISO_8859_1));
            ObjectInputStream objIn = new ObjectInputStream(byteIn);
            return Optional.ofNullable((T) objIn.readObject());
        } catch (IOException e) {
            return Optional.empty();
        } catch (ClassNotFoundException e) {
            LOG.error("类型转化错误");
            return Optional.empty();
        }
    }
}
