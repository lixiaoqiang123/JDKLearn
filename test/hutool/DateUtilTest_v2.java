package hutool;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtilTest_v2 {

    @Test
    public void testCaculateTimeWithOutWeekendAndHoliday(){
        String s0 = testTime("2024-04-22 10:10:10","2024-05-01","2024-05-05","2024-04-28","2024-04-28",5);
        Assert.assertEquals("2024-04-28 10:10:10",s0);
        String s1 = testTime("2024-04-24 10:10:10","2024-05-01","2024-05-05","2024-04-28","2024-04-28",5);
        Assert.assertEquals("2024-04-30 10:10:10",s1);
        String s2 = testTime("2024-04-26 10:10:10","2024-05-01","2024-05-05","2024-04-28","2024-04-28",5);
        Assert.assertEquals("2024-05-07 10:10:10",s2);
        String s3 = testTime("2024-05-01 10:10:10","2024-05-01","2024-05-05","2024-04-28","2024-04-28",5);
        Assert.assertEquals("2024-05-10 10:10:10",s3);
        String s4 = testTime("2024-05-03 10:10:10","2024-05-01","2024-05-05","2024-04-28","2024-04-28",5);
        Assert.assertEquals("2024-05-10 10:10:10",s4);
        String s5 = testTime("2024-05-06 10:10:10","2024-05-01","2024-05-05","2024-04-28","2024-04-28",5);
        Assert.assertEquals("2024-05-13 10:10:10",s5);
    }



    /**
     * 日期计算：开始时间 + 承诺天数 + 周末天数 + 不是周末的法定节假日天数
     * applyTime: 收件时间
     * holidayStart: 假期开始时间
     * holidayEnd: 假期结束时间
     * promiseDay: 承诺时间
     */
    public String testTime(String applyTime,String holidayStart,String holidayEnd,String overTimeStart,String overTimeEnd,int promiseDay){
        DateTime applyDateTime = DateUtil.parseDateTime(applyTime);
        holidayStart = holidayStart+" "+ applyTime.split(" ")[1];
        holidayEnd = holidayEnd+" "+ applyTime.split(" ")[1];
        overTimeStart = overTimeStart+" "+ applyTime.split(" ")[1];
        overTimeEnd = overTimeEnd+" "+ applyTime.split(" ")[1];
        for (int i = 1; i <= promiseDay; i++) {
            DateTime dateTime = DateUtil.offsetDay(applyDateTime, i);
            //如果该日期是周末或者节假日，则加1
            if(DateUtil.isWeekend(dateTime) || DateUtil.isIn(dateTime,DateUtil.parseDateTime(holidayStart),DateUtil.parseDateTime(holidayEnd))){
                promiseDay++;
            }
            //如果是加班日，则减1
            if( DateUtil.isIn(dateTime,DateUtil.parseDateTime(overTimeStart),DateUtil.parseDateTime(overTimeEnd))){
                promiseDay--;
            }
        }
        DateTime promiseTime = DateUtil.offsetDay(applyDateTime,promiseDay);
        System.out.println(promiseTime.toString());
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
}
