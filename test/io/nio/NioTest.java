package io.nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NonBlockingIo
 *  面向数据块的处理机制，同步非阻塞模型，服务端的单个线程可以处理多个客户端请求，对IO流的处理速度有极高的提升
 *
 * 管道流：
 *  Buffer(缓冲区)：底层维护数组存储数据；
 *  Channel(通道)：支持读写双向操作；
 *  Selector(选择器)：提供Channel多注册和轮询能力；
 */
public class NioTest {

    private final static String BASE_PATH = "E:\\WorkSpace\\JavaLearn\\test\\io\\";

    @Test
    public void nio_01() throws IOException {
        // 源文件 目标文件
        File source = new File(BASE_PATH+"fileio-02.png") ;
        File target = new File(BASE_PATH+"channel-"+source.getName()) ;

        // 输入字节流通道
        FileInputStream inStream = new FileInputStream(source);
        FileChannel inChannel = inStream.getChannel();

        // 输出字节流通道
        FileOutputStream outStream = new FileOutputStream(target);
        FileChannel outChannel = outStream.getChannel();

        // 直接通道复制
        // outChannel.transferFrom(inChannel, 0, inChannel.size());

        // 缓冲区读写机制
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while (true) {
            // 读取通道中数据到缓冲区
            int in = inChannel.read(buffer);
            if (in == -1) {
                break;
            }
            // 读写切换
            buffer.flip();
            // 写出缓冲区数据
            outChannel.write(buffer);
            // 清空缓冲区
            buffer.clear();
        }
        outChannel.close();
        inChannel.close();
    }
}
