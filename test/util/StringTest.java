package util;

import com.linewell.core.util.JsonUtil;
import org.apache.commons.lang.WordUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringTest {


    @Test
    public void substring(){
        String province = "350000".substring(0, 3);
        String city = "350100".substring(0, 4);
        System.out.println(province);
        System.out.println(city);
    }

    @Test
    public void substring2(){
        String step = "现场勘察,专家评审";
        System.out.println(step.substring(0, step.length() - 1));
    }

    @Test
    public void split(){
        String unid = "123456";
        String[] split = unid.split(",");
        List<String> collect = Arrays.stream(split).filter(s -> s.equals("123456")).collect(Collectors.toList());
        for (int i = 0; i < collect.size(); i++) {
            System.out.println(i);
            System.out.println(collect.get(i));
        }
    }

    @Test
    public void split2(){
        String unid = "123456";
        String[] split = unid.split(",");
        for(String s : split){
            System.out.println(s);
        }
    }

    @Test
    public void stringBuilder(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("123");
        stringBuilder.append(true);
        stringBuilder.append(123);
        stringBuilder.append(123.12);
        System.out.println("sb"+stringBuilder);
    }

    @Test
    public void valueOf(){
        String value = "-1";
        Integer integer = Integer.valueOf(value);
        System.out.println(integer);
    }

    @Test
    public void matcher(){
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher("福建南威软件有限公司");
        boolean b = m.find();
        System.out.println(b);
    }

    @Test
    public void trim(){
        String xxx = " 123 ";
        System.out.println(xxx.trim());
    }
    
    @Test
    public void transferEncoding() throws UnsupportedEncodingException {
        //获取本机编码，启动jar包时-Dfile-encoding=UTF-8
        String charSet = Charset.defaultCharset().name();
        System.out.println(charSet);

        String name = "鏉庢檽寮�";
        byte[] bytes = name.getBytes("GBK");
        String s = new String(bytes, "UTF-8");
        System.out.println(s);
        String xxx = "浜ゆ槗鎴愬姛";
        xxx = new String(xxx.getBytes("GBK"),"UTF-8");
        System.out.println(xxx);
    }


    @Test
    public void convert(){
        String word = "parentCategoryId";
        System.out.println(WordUtils.capitalize(word));
    }

    //https://was.hnzwfw.gov.cn/book/front/appointment/index?code=90001
    @Test
    public void isReliableUrl() {
        String[] reliableUrl = {"was.hnzwfw.gov.cn"};
        String targetUrl = "http://was.hnzwfw.gov.cn/book/front/appointment/index?code=90001";
        String newUrl = "";
        if (targetUrl.startsWith("https")) {
            newUrl = targetUrl.substring(8);
        }else if (targetUrl.startsWith("http")) {
            newUrl = targetUrl.substring(7);
        }
        System.out.println(newUrl);
        for (int i = 0; i < reliableUrl.length; i++) {
            String s = reliableUrl[i];
            if (newUrl.startsWith(s)) {
                System.out.println(true);
                return;
            }
        }
        System.out.println(false);
        return;
    }

    @Test
    public void substring3(){
        String substring = "410000Y001".substring(6,10);
        System.out.println(substring);
    }
}
