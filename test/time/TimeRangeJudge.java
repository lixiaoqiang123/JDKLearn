package time;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

public class TimeRangeJudge {

    public static void main(String[] args) {
        String receivetime = "2024-02-20 12:31:10";
        DateTime dateTime = DateUtil.parse(receivetime);
        System.out.println(DateUtil.between(cn.hutool.core.date.DateUtil.parse(receivetime),new Date(), DateUnit.WEEK)>=1);
        System.out.println(dateTime.toCalendar().getTime());
        System.out.println(dateTime.toCalendar().get(DateField.MONTH.getValue()));
    }
}
