package com.mark.nt.action;

import io.netty.channel.*;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by lulei on 2016/3/15.
 */
public class WebSocketServerInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast(new HttpServerCodec(),
                new HttpObjectAggregator(65535),
                new WebSocketServerProtocolHandler("/websocket"));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        File file = new File("text.txt");
        FileInputStream fis = new FileInputStream(file);
        FileRegion region = new DefaultFileRegion(fis.getChannel(),0,file.length());

        Channel channel= ctx.channel();
        channel.writeAndFlush(region).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if(!future.isSuccess()){
                    Throwable cause = future.cause();
                }
            }
        });
    }
}
