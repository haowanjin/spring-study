package com.tman.javastudy.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author wanjin.hao@woqutech.com
 * @Date 2020/11/5
 */
public class NioClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);
        if (!socketChannel.connect(address)) {/**连接服务端*/
            while (!socketChannel.finishConnect()) {
                System.out.println("因为连接需要时间，客户端不会阻塞，可以做其他事情。。。。");
            }
        }
        String msg = "Hello，World";
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        /**发送数据*/
        socketChannel.write(buffer);
        System.in.read();//客户端等待
    }
}
