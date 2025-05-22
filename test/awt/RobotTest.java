package awt;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RobotTest {

    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();
        // 时间范围  上班时间
        String exeTimeStr = "083000-200000";
        while (isExeTime(exeTimeStr)){
            // 模拟双击鼠标 这种比较合理
            // robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
            //robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);

            //模拟键盘输入
            //robot.keyPress(KeyEvent.VK_C);

            //模拟自动截图
            BufferedImage screenCapture = robot.createScreenCapture(new Rectangle(10, 10, 10, 10));
            boolean screenCaptureResult = false;
            try {
                screenCaptureResult = ImageIO.write(screenCapture, "png", new File("E:\\save.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(screenCaptureResult);
            //频率 60s一次
            robot.delay(10000);
        }
    }

    /**
     * 判断是否在时间范围内
     * @param exeTimeStr
     * @return
     */
    private static boolean isExeTime(String exeTimeStr){
        String currentTime = getCurrentTime("HHmmss");
        int currentTimeInt = Integer.parseInt(currentTime);
        String[] timeArr = exeTimeStr.split("-");
        if (timeArr.length!=2){
            throw new RuntimeException("exeTimeStr时间输入有误" + exeTimeStr);
        }
        int startTimeInt =  Integer.parseInt(timeArr[0]);
        int endTimeInt =  Integer.parseInt(timeArr[1]);
        return startTimeInt <= currentTimeInt &&  endTimeInt >= currentTimeInt;
    }

    /**
     * 格式化当前时间
     * @param formatStr
     * @return
     */
    private static String getCurrentTime(String formatStr) {
        DateFormat sdf = new SimpleDateFormat(formatStr);
        Date date = Calendar.getInstance().getTime();
        return sdf.format(date);
    }
}
