package com.tman.javastudy.netty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;

public class NettyClient {
    private String host;
    private int port;

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
        // 配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 客户端辅助启动类 对客户端配置
            Bootstrap client = new Bootstrap();
            client.group(group)//
                    .channel(NioSocketChannel.class)//
                    .option(ChannelOption.TCP_NODELAY, true)//
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("decoder", new ByteArrayDecoder());//new MessageProtocolDecoder());
                            socketChannel.pipeline().addLast("channelHandler", new ClientHandler()); // 处理网络IO
                            socketChannel.pipeline().addLast("encoder", new ByteArrayEncoder());//new MessageProtocolEncoder());
                        }
                    });
            // 异步链接服务器 同步等待链接成功
            ChannelFuture result  = client.connect(host, port).sync();

            // 等待链接关闭
            result.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully();
            System.out.println("client release resource...");
        }

    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            new NettyClient("127.0.0.1", 8081).run();
        }
    }

}