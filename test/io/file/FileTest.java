package io.file;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class FileTest {

    private final static String FILE_PATH = "E:\\WorkSpace\\JavaLearn\\test\\io\\userUnid.log";

    private final static String FILE_DIR = "E:\\WorkSpace\\JavaLearn\\test\\io\\";

    @Test
    public void fileTest(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("E:\\WorkSpace\\JavaLearn\\test\\io\\userUnids.log"));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            File file = new File(FILE_PATH);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("\"");
                System.out.println(split[1]);
                bufferedWriter.write("\'"+split[1]+"\',");
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        // 1、读取指定文件
        File speFile = new File(FILE_DIR+"fileio-03.text") ;
        if (!speFile.exists()){
            boolean creFlag = speFile.createNewFile() ;
            System.out.println("创建："+speFile.getName()+"; 结果："+creFlag);
        }

        // 2、读取指定位置
        File dirFile = new File(FILE_DIR) ;
        // 判断是否目录
        boolean dirFlag = dirFile.isDirectory() ;
        if (dirFlag){
            File[] dirFiles = dirFile.listFiles() ;
            printFileArr(dirFiles);
        }

        // 3、删除指定文件
        if (speFile.exists()){
            boolean delFlag = speFile.delete() ;
            System.out.println("删除："+speFile.getName()+"; 结果："+delFlag);
        }
    }
    private static void printFileArr (File[] fileArr){
        if (fileArr != null && fileArr.length>0){
            for (File file : fileArr) {
                printFileInfo(file) ;
            }
        }
    }
    private static void printFileInfo (File file) {
        System.out.println("名称："+file.getName());
        System.out.println("长度："+file.length());
        System.out.println("路径："+file.getPath());
        System.out.println("文件判断："+file.isFile());
        System.out.println("目录判断："+file.isDirectory());
        System.out.println("最后修改："+new Date(file.lastModified()));
        System.out.println();
    }

}
