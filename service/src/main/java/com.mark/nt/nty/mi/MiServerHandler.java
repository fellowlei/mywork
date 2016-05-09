package com.mark.nt.nty.mi;

import com.mark.frame.FastJsonUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by lulei on 2016/3/25.
 */
public class MiServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
        MiMessage msg = (MiMessage) obj;
        System.out.println("receive: " + FastJsonUtils.toJson(msg));
//        String from = msg.getFrom();
//        String to = msg.getTo();
        if(msg.getType().equals("open")){
            MiResponse response = genMIResponse(msg.getId(),1,"invoke","todo");
            ctx.writeAndFlush(response);
        }else if(msg.getType().equals("talk")){
            MIPage.sendTo(msg);
            MiResponse response = genMIResponse(msg.getId(),1,"close","to close");
            ctx.writeAndFlush(response);
        }else if(msg.getType().equals("close")){
            MiResponse response = genMIResponse(msg.getId(),1,"ok","is ok");
            ctx.writeAndFlush(response);
        }else{
            System.out.println("server: " + FastJsonUtils.toJson(msg));
        }

        MIPage.dump();


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private MiResponse genMIResponse(Long id,Integer result,String type,String msg) {
        MiResponse response = new MiResponse();
        response.setId(id);
        response.setResult(result);
        response.setType(type);
        response.setMessage(msg);
        return response;
    }

}
