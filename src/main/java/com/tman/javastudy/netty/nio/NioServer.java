package com.tman.javastudy.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author wanjin.hao@woqutech.com
 * @Date 2020/11/5
 */
public class NioServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        /**非阻塞*/
        serverSocketChannel.configureBlocking(false);
        /**
         * 将 channel 注册到 selector，保存在 keys 集合中
         */
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, ByteBuffer.allocate(1024));

        while (true) {
            /**该方法会返回发生了事件的 channel，并将发生的 channel 的 keySelectionKey
             * 存到 selector 的 selectionKeys 集合中，并返回发生事件的 channel 的个数
             */
            if (selector.select(1000) == 0) {
                System.out.println("------服务端等待了1秒，无连接-----");
                continue;
            }
            /**
             * 监听到发生事件的 channel，返回值 >0
             * selectedKeys() 方法返回发生事件的 channel 的集合
             * 遍历集合，通过 selectionKey 反向获取 channel
             */
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keys = selectionKeys.iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                /**当前有客户端发起连接事件*/
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    /**接收连接，accept 方法是阻塞方法，因为现在有连接所以才等待*/
                    SocketChannel socketChannel = channel.accept();
                    System.out.println("------接收到一个连接----");
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                /**
                 * channel 有数据可读
                 */
                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();

                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("接收到客户端数据--->"+new String(buffer.array()));
                }

                keys.remove();
            }
        }
    }
}
