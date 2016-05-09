package com.mark.nt.nty.ch5;

import com.mark.frame.FastJsonUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by lulei on 2016/3/22.
 */
public class SubReqServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReq req = (SubscribeReq) msg;
        if ("mark".equalsIgnoreCase(req.getUserName())) {
            System.out.println("server accept client req: " + FastJsonUtils.toJson(req));
            ctx.writeAndFlush(resp(req.getSubReqId()));
        }
        else{
            System.out.println("message: " + FastJsonUtils.toJson(req));
        }
    }

    private Object resp(int subReqId) {
        SubscribeResp resp = new SubscribeResp();
        resp.setSubReqId(subReqId);
        resp.setRespCode(0);
        resp.setDesc("netty book order success,3 days later");
        return resp;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
