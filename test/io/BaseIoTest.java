package io;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 基础io
 *
 *  字节流
 *  字符流
 *  管道流
 */
public class BaseIoTest {

    private final static String FILE_DIR = "E:\\WorkSpace\\JavaLearn\\test\\io\\";

    /**
     * 字节流
     *
     * 字节流：InputStream输入、OutputStream输出；数据传输的基本单位是字节；
     * 字节流应用场景：数据是文件本身，例如图片，视频，音频等。
     */
    @Test
    public void IoByte_01() throws Exception {
        // 源文件 目标文件
        File source = new File(FILE_DIR+"fileio-01.png") ;
        File target = new File(FILE_DIR+"copy-"+source.getName()) ;
        // 输入流 输出流
        InputStream inStream = new FileInputStream(source) ;
        OutputStream outStream = new FileOutputStream(target) ;
        // 读入 写出
        byte[] byteArr = new byte[1024];
        int readSign ;
        while ((readSign=inStream.read(byteArr)) != -1){
            outStream.write(byteArr);
        }
        // 关闭输入、输出流
        outStream.close();
        inStream.close();
    }

    /**
     * 字节流缓冲
     */
    @Test
    public void IoByte_02() throws Exception {
// 源文件 目标文件
        File source = new File(FILE_DIR+"fileio-02.png") ;
        File target = new File(FILE_DIR+"backup-"+source.getName()) ;
        // 缓冲：输入流 输出流
        InputStream bufInStream = new BufferedInputStream(new FileInputStream(source));
        OutputStream bufOutStream = new BufferedOutputStream(new FileOutputStream(target));
        // 读入 写出
        int readSign ;
        while ((readSign=bufInStream.read()) != -1){
            bufOutStream.write(readSign);
        }
        // 关闭输入、输出流
        bufOutStream.close();
        bufInStream.close();
    }

    /**
     * 字节流
     *
     * 字符流：Reader读取、Writer写出；数据传输的基本单位是字符；
     *
     * 字符流应用场景：文件作为数据的载体，例如Excel、CSV、TXT等。
     */
    @Test
    public void IoChar_01() throws Exception {
        // 读文本 写文本
        File readerFile = new File(FILE_DIR+"io-text.txt") ;
        File writerFile = new File(FILE_DIR+"copy-"+readerFile.getName()) ;
        // 字符输入输出流
        Reader reader = new FileReader(readerFile) ;
        Writer writer = new FileWriter(writerFile) ;
        // 字符读入和写出
        int readSign ;
        while ((readSign = reader.read()) != -1){
            writer.write(readSign);
        }
        writer.flush();
        // 关闭流
        writer.close();
        reader.close();
    }

    /**
     * 字节缓冲流
     */
    @Test
    public void IoChar_02() throws Exception {
        // 读文本 写文本
        File readerFile = new File(FILE_DIR+"io-text.txt") ;
        File writerFile = new File(FILE_DIR+"line-"+readerFile.getName()) ;
        // 缓冲字符输入输出流
        BufferedReader bufReader = new BufferedReader(new FileReader(readerFile)) ;
        BufferedWriter bufWriter = new BufferedWriter(new FileWriter(writerFile)) ;
        // 字符读入和写出
        String line;
        while ((line = bufReader.readLine()) != null){
            bufWriter.write(line);
            bufWriter.newLine();
        }
        bufWriter.flush();
        // 关闭流
        bufWriter.close();
        bufReader.close();
    }

    /**
     * 编解码
     */
    @Test
    public void EnDeCode_01() throws Exception {
        String var = "IO流" ;
        // 编码
        byte[] enVar = var.getBytes(StandardCharsets.UTF_8) ;
        for (byte encode:enVar){
            System.out.println(encode);
        }
        // 解码
        String deVar = new String(enVar,StandardCharsets.UTF_8) ;
        System.out.println(deVar);
        // 乱码
        String messyVar = new String(enVar,StandardCharsets.ISO_8859_1) ;
        System.out.println(messyVar);
    }

    /**
     * 序列化
     */
    @Test
    public void Serialize() throws Exception {
        // 序列化对象
        OutputStream outStream = new FileOutputStream("SerEntity.txt") ;
        ObjectOutputStream objOutStream = new ObjectOutputStream(outStream);
        objOutStream.writeObject(new SerEntity(1,"Cicada"));
        objOutStream.close();
        // 反序列化对象
        InputStream inStream = new FileInputStream("SerEntity.txt");
        ObjectInputStream objInStream = new ObjectInputStream(inStream) ;
        SerEntity serEntity = (SerEntity) objInStream.readObject();
        System.out.println(serEntity);
        inStream.close();
    }

    class SerEntity implements Serializable {
        private Integer id ;
        private String name ;

        public SerEntity(int i, String cicada) {
            this.id = i;
            this.name = cicada;
        }
    }
}
