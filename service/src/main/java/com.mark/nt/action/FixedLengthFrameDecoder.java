package com.mark.nt.action;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by lulei on 2016/3/15.
 */
public class FixedLengthFrameDecoder extends ByteToMessageDecoder{
    private final int frameLength;

    public FixedLengthFrameDecoder(int frameLength) {
        if(frameLength <= 0){
            throw new RuntimeException("frameLength must a positive value" + frameLength);
        }
        this.frameLength = frameLength;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        while(byteBuf.readableBytes() >= frameLength){
            ByteBuf buf = byteBuf.readBytes(frameLength);
            list.add(buf);
        }
    }
}
