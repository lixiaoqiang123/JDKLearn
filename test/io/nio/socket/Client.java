package io.nio.socket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {


    public static void main(String[] args) {
        try {
            // 连接服务端
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8089));
            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            String conVar = "[hello-8089]";
            writeBuffer.put(conVar.getBytes());
            writeBuffer.flip();
            // 每隔5S发送一次数据
            while (true) {
                Thread.sleep(5000);
                writeBuffer.rewind();
                socketChannel.write(writeBuffer);
                writeBuffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
