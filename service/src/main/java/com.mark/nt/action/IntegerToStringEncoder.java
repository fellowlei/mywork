package com.mark.nt.action;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Created by lulei on 2016/3/15.
 */
public class IntegerToStringEncoder extends MessageToMessageEncoder<Integer> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {
        out.add(String.valueOf(msg));
    }
}
