package com.mark.nt.nty.mi;

import com.mark.frame.FastJsonUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by lulei on 2016/3/25.
 */
public class MiClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i=0; i<3; i++){
            MiMessage msg = genTypeMsg("open");
            ctx.write(msg);
            ctx.flush();
        }

    }

    private MiMessage genTypeMsg(String type) {
        MiMessage msg = new MiMessage();
        msg.setId(IDGen.getNextId());
        msg.setType(type);
        return msg;
    }

    private MiMessage genTalk(String from, String to, String message) {
        MiMessage msg = new MiMessage();
        msg.setId(IDGen.getNextId());
        msg.setFrom(from);
        msg.setTo(to);
        msg.setType("talk");
        msg.setMessage(message);
        return msg;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
        MiResponse msg = (MiResponse) obj;
        System.out.println("client receive: " + FastJsonUtils.toJson(msg));
        if (msg.getType().equals("invoke")) {
            ctx.writeAndFlush(genTalk("mark2" , "john2", "welcome " + msg.getId()));
        } else if (msg.getType().equals("close")) {
            ctx.writeAndFlush(genTypeMsg("close"));
        } else if (msg.getType().equals("ok")) {
            System.out.println("connect over");
        } else {
            System.out.println("normal");
        }

    }
}
