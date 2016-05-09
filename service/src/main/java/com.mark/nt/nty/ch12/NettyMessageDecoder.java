package com.mark.nt.nty.ch12;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.marshalling.MarshallingDecoder;

/**
 * Created by lulei on 2016/3/24.
 */
public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {
    MarshallingDecoder marshallingDecoder;


    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
//        marshallingDecoder = new MarshallingDecoder();
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf frame = (ByteBuf) super.decode(ctx,in);
        if(frame == null){
            return null;
        }
        return null;
    }
}
