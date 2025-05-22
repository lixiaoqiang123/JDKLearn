package hutool;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;

public class DateUtilTest {

    @Test
    public void testCaculateTimeWithOutWeekendAndHoliday(){
        //String s = testTime("2024-04-24 10:10:10","2024-05-01","2024-05-05",5);
        //String s = testTime("2024-04-26 10:10:10","2024-05-01","2024-05-05",5);
        String s = testTime("2024-05-01 10:10:10","2024-05-01","2024-05-05",5);
        System.out.println(s);
        //String s = testTime("2024-09-13 10:10:10","","",3);
        //Assert.assertEquals("2024-09-18 10:10:10",s);
    }

    @Test
    public void validateTime(){
        String time = "2024-12-12 12:12";
        System.out.println(isValidDateTime(time));
        String time2 = "2024-12-12 ";
        System.out.println(isValidDateTime(time2));
        String time3 = "2024-12-12 12:12:12";
        System.out.println(isValidDateTime(time3));
        String time4 = "2024-12";
        System.out.println(isValidDateTime(time4));
        String time5 = "2024-12-12 12";
        System.out.println(isValidDateTime(time5));
    }



    /**
     * 日期计算：开始时间 + 承诺天数 + 周末天数 + 不是周末的法定节假日天数
     * applyTime: 收件时间
     * holidayStart: 假期开始时间
     * holidayEnd: 假期结束时间
     * promiseDay: 承诺时间
     */
    public String testTime(String applyTime,String holidayStart,String holidayEnd,int promiseDay){
        holidayStart = holidayStart+" "+ applyTime.split(" ")[1];
        //System.out.println(holidayStart);
        holidayEnd = holidayEnd+" "+ applyTime.split(" ")[1];
        DateTime dateTime = DateUtil.offsetDay(DateUtil.parse(applyTime, "yyyy-MM-dd HH:mm:ss"),promiseDay);
        // 获取中间的日期
        Date[] dates = dates(applyTime, dateTime.toString());
        // 判断里面有几天周末
        int weekendCount = 0;
        for (int i = 0; i < dates.length; i++) {
            if( DateUtil.isWeekend(dates[i])){
                weekendCount ++;
            }
        }
        //如果最后一天是周六，多加一天,跳过周末
        if(DateUtil.dayOfWeek(dates[dates.length-1])== 7){
            weekendCount ++;
        }
        //加上周末
        DateTime endTime = DateUtil.offsetDay(dateTime,weekendCount);
        //判断里面节假日天数
        int holidayCount = 0;
        Date[] checkHoliday = dates(applyTime, endTime.toString());
        if(StringUtils.isNotBlank(holidayStart) && StringUtils.isNotBlank(holidayEnd)){
            for(int i = 0; i < checkHoliday.length; i++){
                //如果是周末则跳过
                if(DateUtil.isWeekend(checkHoliday[i])){
                    continue;
                }
                //如果不是周末，则加1
                if(DateUtil.isIn(checkHoliday[i],DateUtil.parseDateTime(holidayStart),DateUtil.parseDateTime(holidayEnd))){
                    holidayCount++;
                }
            }
        }
        DateTime promiseTime = DateUtil.offsetDay(endTime,holidayCount);
        //如果结束日期在假期内，
//        if(DateUtil.isIn(promiseTime,DateUtil.parseDateTime(holidayStart),DateUtil.parseDateTime(holidayEnd))){
//            promiseTime = DateUtil.offsetDay(DateUtil.parseDateTime(holidayEnd),promiseDay-weekendCount-holidayCount);
//        }
        return promiseTime.toString();
    }

    /**
     * 两个日期之间的全部日期数值数组
     *
     * @param begin
     * @param end
     * @return{2010-10-10, 2010-10-11, 2010-10-12, ,...,2011-01-11}
     */
    public Date[] dates(String begin, String end) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date[] arr = new Date[] {  };
        try {
            Date db = df.parse(begin);
            Calendar cb = Calendar.getInstance();
            cb.setTime(db);
            Date de = df.parse(end);
            Calendar ce = Calendar.getInstance();
            ce.setTime(de);
            long lday = (ce.getTimeInMillis() - cb.getTimeInMillis())
                    / (1000 * 60 * 60 * 24);
            int lg = Long.valueOf(lday).intValue();
            arr = new Date[lg + 1];
            arr[0] = cb.getTime();
            for (int i = 0; i < lg; i++) {
                cb.add(Calendar.DAY_OF_YEAR, 1);
                arr[i + 1] = cb.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public static boolean isValidDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            LocalDateTime.parse(dateTime, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
