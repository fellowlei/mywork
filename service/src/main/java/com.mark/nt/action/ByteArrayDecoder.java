package com.mark.nt.action;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Created by lulei on 2016/3/15.
 */
public class ByteArrayDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        byte[] array = new byte[msg.readableBytes()];
        msg.getBytes(0, array);
        out.add(array);
    }

    static class ByteArrayEncoder extends MessageToMessageEncoder<byte[]> {

        @Override
        protected void encode(ChannelHandlerContext ctx, byte[] msg, List<Object> out) throws Exception {
            out.add(Unpooled.wrappedBuffer(msg));
        }
    }
}
