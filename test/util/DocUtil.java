package util;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.documents.TextSelection;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class DocUtil {

    public static void main(String[] args) {
        Document doc = new Document();
        doc.loadFromFile("E:\\WorkSpace\\JavaLearn\\test.docx", FileFormat.Docx);
        Map<String, String> map = new HashMap<>();
        //普通替换
        map.put("name", "张山");
        map.put("age", "15");
        map.put("address", "测试地址");
        replaceSpecialWord(doc, map);//字符替换
    }

    public static void replaceSpecialWord(Document doc, Map<String, String> map) {
        // 正则表达式，匹配所有的占位符 ${}
        Pattern pattern = Pattern.compile("\\$\\{.*?}");
        // 根据正则表达式获取所有文本
        TextSelection[] allPattern = doc.findAllPattern(pattern);
        // 逐个替换占位符
        for (TextSelection textSelection : allPattern) {
            String target = textSelection.getSelectedText();
            String tmp = map.get(target.replace("${","").replace("}",""));
            System.out.print(textSelection.getSelectedText());
            int res = doc.replace(textSelection.getSelectedText(), tmp, true, true);
            System.out.println(":" + res);
        }
        try {
            File file = new File("E:\\WorkSpace\\JavaLearn\\test2.docx");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            doc.saveToStream(fileOutputStream, FileFormat.Docx);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
