package io.nio.socket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {

    /**
     * SelectionKey绑定Selector和Chanel之间的关联，并且可以获取就绪状态下的Channel集合。
     */
    public static void main(String[] args) {
        try {
            //启动服务开启监听
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            socketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 8089));
            // 设置非阻塞，接受客户端
            socketChannel.configureBlocking(false);
            // 打开多路复用器
            Selector selector = Selector.open();
            // 服务端Socket注册到多路复用器，指定兴趣事件
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            // 多路复用器轮询
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            while (selector.select() > 0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> selectionKeyIter = selectionKeys.iterator();
                while (selectionKeyIter.hasNext()){
                    SelectionKey selectionKey = selectionKeyIter.next() ;
                    selectionKeyIter.remove();
                    if(selectionKey.isAcceptable()) {
                        // 接受新的连接
                        SocketChannel client = socketChannel.accept();
                        // 设置读非阻塞
                        client.configureBlocking(false);
                        // 注册到多路复用器
                        client.register(selector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()) {
                        // 通道可读
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        int len = client.read(buffer);
                        if (len > 0){
                            buffer.flip();
                            byte[] readArr = new byte[buffer.limit()];
                            buffer.get(readArr);
                            System.out.println(client.socket().getPort() + "端口数据:" + new String(readArr));
                            buffer.clear();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
