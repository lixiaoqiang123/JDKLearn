package text.simpledateformate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 使用DateTimeFormatter代替SimpleDateFormat
 *
 * DateTimeFormatter是线程安全的，默认提供了很多格式化方法，也可以通过ofPattern方法创建自定义格式化方法。
 */
public class SimpleDateFormateThreadSafe_02 {
    public static void main(String[] args) {
        //格式化日期
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime); // 2019-11-20T15:04:29.017
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String strDate=localDateTime.format(dtf);
        System.out.println(strDate); // 2019/23/20 15:23:46
        //解析日期
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime localDateTime2=LocalDateTime.parse("2019/11/20 15:23:46",formatter);
        System.out.println(localDateTime2); // 2019-11-20T15:23:46
    }
}
