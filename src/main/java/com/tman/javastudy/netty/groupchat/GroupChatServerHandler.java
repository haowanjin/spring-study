package com.tman.javastudy.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;


/**
 * Created by IntelliJ IDEA.
 *
 * @Author wanjin.hao@woqutech.com
 * @Date 2020/11/11
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {
    private Logger log = LoggerFactory.getLogger(GroupChatServer.class);
    //定义一个 channel 组，管理所有的 channel
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 一旦建立连接首先被执行
     */
    // 将当前的 channel 添加到 channelGroup
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将该客户加入聊天的信息推送给其他在线的客户端
        channelGroup.writeAndFlush(String.format("[客户端] %s 加入聊天\n", channel.remoteAddress()));
        channelGroup.add(channel);
    }

    /**
     * 表示 channel 处于活动状态，提示 xxx 上线
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("========== {} 上线了 =========", ctx.channel().remoteAddress());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("========== {} 离开了 =========", ctx.channel().remoteAddress());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (ch != channel) {
                ch.writeAndFlush(String.format("[客户端] %s 发送了消息：%s", channel.remoteAddress(), msg));
            } else {
                ch.writeAndFlush(String.format("[自己]发送了消息：%s", msg));
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
