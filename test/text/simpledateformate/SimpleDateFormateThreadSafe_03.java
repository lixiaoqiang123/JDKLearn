package text.simpledateformate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 使用JDK8全新的日期和时间API
 *
 * 　　1、LocalDate、LocalTime、LocalDateTime
 *
 * 　　Date如果不格式化，打印出的日期可读性极差，可使用LocalDateTime代替。
 */
public class SimpleDateFormateThreadSafe_03 {

    public static void main(String[] args) {
        //（1）LocalDateTime：获取年月日时分秒等于LocalDate+LocalTime
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime); // 2019-11-20T15:04:29.017
        LocalDate localDate = localDateTime.toLocalDate();
        LocalTime localTime = localDateTime.toLocalTime();
        //LocalDate：获取年月日
        LocalDate localDate2=LocalDate.now();
        System.out.println(localDate2); // 2019-11-20
        //LocalTime：获取时分秒
        LocalTime localTime3 = LocalTime.now();
        System.out.println(localTime3); // 15:14:17.081
        int hour = localTime3.getHour();
        int minute = localTime3.getMinute();
        int second = localTime3.getSecond();
    }
}
