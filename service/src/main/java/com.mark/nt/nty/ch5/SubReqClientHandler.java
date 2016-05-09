package com.mark.nt.nty.ch5;

import com.mark.frame.FastJsonUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by lulei on 2016/3/22.
 */
public class SubReqClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.write(subReq(i));
        }
        ctx.flush();
    }

    private Object subReq(int i) {
        SubscribeReq req = new SubscribeReq();
        req.setUserName("mark");
        req.setAddress("北京市丰台区");
        req.setPhoneNumber("189********");
        req.setProductName("netty 图书");
        req.setSubReqId(i);
        return req;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("receive response: " + FastJsonUtils.toJson(msg));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
