package com.mark.nt.action;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * Created by lulei on 2016/3/16.
 */
public class LogEventEncoder extends MessageToMessageEncoder<LogEvent> {
    private final InetSocketAddress inetSocketAddress;

    public LogEventEncoder(InetSocketAddress address) {
        this.inetSocketAddress = address;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, LogEvent msg, List<Object> out) throws Exception {
        ByteBuf buf = ctx.alloc().buffer();
        buf.writeBytes(msg.getLogfile().getBytes(CharsetUtil.UTF_8));
        buf.writeByte(LogEvent.SEPARATOR);
        buf.writeBytes(msg.getMsg().getBytes(CharsetUtil.UTF_8));
        out.add(new DatagramPacket(buf, inetSocketAddress));
    }


}
