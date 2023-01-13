package com.tman.javastudy.netty.groupchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author wanjin.hao@woqutech.com
 * @Date 2020/11/11
 */
public class GroupChatServer {
    private Logger log = LoggerFactory.getLogger(GroupChatServer.class);
    private int port;

    public GroupChatServer(int port) {
        this.port = port;
    }

    public void run() throws InterruptedException {
        /**创建线程组*/
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            /**获取 pipeLine*/
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast("decoder", new StringDecoder());
                            pipeline.addLast("encoder", new StringEncoder());

                            //向 pipeLine 添加自己的业务处理器
                            pipeline.addLast(new GroupChatServerHandler());//TODO
                        }
                    });
            ChannelFuture future = bootstrap.bind(port).sync();
            log.info("=======服务器启动==========");
            //监听服务器停止
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new GroupChatServer(6666).run();
    }
}
