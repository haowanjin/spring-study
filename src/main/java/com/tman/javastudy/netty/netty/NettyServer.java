package com.tman.javastudy.netty.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class NettyServer {
    private int port;

    public NettyServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("idleStateHandler", new IdleStateHandler(60, 0, 0, TimeUnit.SECONDS));
                            socketChannel.pipeline().addLast("decoder", new ByteArrayDecoder());
                            socketChannel.pipeline().addLast("channelHandler", new ServerHandler());
                            socketChannel.pipeline().addLast("encoder", new ByteArrayEncoder());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture cf = bootstrap.bind(port).sync();
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }


    public static void main(String[] args) throws Exception {
        new NettyServer(8081).run();
    }

}
