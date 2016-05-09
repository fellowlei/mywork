package com.mark.nt.nty.ch10;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.ThreadLocalRandom;

/**
 * Created by lulei on 2016/3/24.
 */
public class ChinesProverbServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    private static final String[] dic = {"test1", "test2", "test3"};

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
        String req = packet.content().toString(CharsetUtil.UTF_8);
        System.out.println(req);
        if ("mark".equals(req)) {
            ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("result: " + nextQuote(), CharsetUtil.UTF_8), packet.sender()));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private String nextQuote() {
        int quoteId = ThreadLocalRandom.current().nextInt(dic.length);
        return dic[quoteId];
    }
}
