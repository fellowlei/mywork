package com.mark.nt.action;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by lulei on 2016/3/14.
 */
public class NotSharaableHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("read called");
        ctx.fireChannelRead(msg);
    }
}
