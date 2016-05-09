package com.mark.nt.action;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by lulei on 2016/3/16.
 */
public class LogEventHandler extends SimpleChannelInboundHandler<LogEvent> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogEvent msg) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(msg.getReceived());
        sb.append("msg" + msg.toString());
        System.out.println(sb.toString());
    }
}
