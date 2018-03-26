package com.mark.nt.nty2.ch9;

import com.sun.javafx.image.ByteToBytePixelConverter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by lulei on 2017/7/27.
 */
public class FixedLengthFrameDecoder extends ByteToMessageDecoder
{
    private final int frameLength;

    public FixedLengthFrameDecoder(int frameLength) {
        this.frameLength = frameLength;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        while (in.readableBytes() >= frameLength){
            ByteBuf buf = in.readBytes(frameLength);
            out.add(buf);
        }
    }

    public static void main(String[] args) {
        ByteBuf buf = Unpooled.buffer();
        for(int i=0; i<9; i++){
            buf.writeByte(i);
        }
        ByteBuf input = buf.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        System.out.println(channel.writeInbound(input.readBytes(2)));
        System.out.println(channel.writeInbound(input.readBytes(7)));
        System.out.println(channel.finish());

        ByteBuf read = (ByteBuf) channel.readInbound();
        System.out.println(buf.readSlice(3).equals(read));
        read.release();

        read = (ByteBuf) channel.readInbound();
        System.out.println(buf.readSlice(3).equals(read));
        read.release();

        read = (ByteBuf)channel.readInbound();
        System.out.println(buf.readSlice(3).equals(read));
        read.release();

//        System.out.println(channel.readInbound());
        buf.release();

    }

    public void test1(){
        ByteBuf buf = Unpooled.buffer();
        for(int i=0; i<9; i++){
            buf.writeByte(i);
        }
        ByteBuf input = buf.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        System.out.println(channel.writeInbound(input.retain()));
        System.out.println(channel.finish());

        ByteBuf read = (ByteBuf) channel.readInbound();
        System.out.println(buf.readSlice(3).equals(read));
        read.release();

        read = (ByteBuf) channel.readInbound();
        System.out.println(buf.readSlice(3).equals(read));
        read.release();

        read = (ByteBuf)channel.readInbound();
        System.out.println(buf.readSlice(3).equals(read));
        read.release();

//        System.out.println(channel.readInbound());
//        buf.release();
    }
}
