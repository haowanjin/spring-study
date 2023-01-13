package com.tman.javastudy.netty.groupchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author wanjin.hao@woqutech.com
 * @Date 2020/11/11
 */
public class GroupChatClient {
    private Logger log = LoggerFactory.getLogger(GroupChatClient.class);
    private String host;
    private int port;

    public GroupChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws InterruptedException {
        NioEventLoopGroup clientGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(clientGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();
                            pipeline.addLast("decoder", new StringDecoder());
                            pipeline.addLast("encoder", new StringEncoder());

                            //向 pipeLine 添加自己的业务处理器
                            pipeline.addLast(new GroupChatClientHandler());//TODO
                        }
                    });
            ChannelFuture future = bootstrap.connect(host, port).sync();
            log.info("========客户端启动===========");
            Channel channel = future.channel();
            Scanner in = new Scanner(System.in);
            while (in.hasNext()) {
                channel.writeAndFlush(String.format("%s \r\n", in.nextLine()));
            }
        } finally {
            clientGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new GroupChatClient("127.0.0.1", 6666).run();
    }

}
