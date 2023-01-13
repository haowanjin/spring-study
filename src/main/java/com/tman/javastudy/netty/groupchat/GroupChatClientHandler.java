package com.tman.javastudy.netty.groupchat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author wanjin.hao@woqutech.com
 * @Date 2020/11/11
 */
public class GroupChatClientHandler extends SimpleChannelInboundHandler<String> {
    private Logger log = LoggerFactory.getLogger(GroupChatClient.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        log.info(msg.trim());
    }
}
