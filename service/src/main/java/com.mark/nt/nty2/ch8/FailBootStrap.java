package com.mark.nt.nty2.ch8;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.oio.OioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by lulei on 2017/7/26.
 */
public class FailBootStrap {
    public static void main(String[] args) {
        EventLoopGroup group =new NioEventLoopGroup();
        Bootstrap bootstrap =new Bootstrap();
        bootstrap.group(group).channel(OioSocketChannel.class) // fix to NIO
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                        System.out.println("received data");
                    }
                });
        ChannelFuture channelFuture =  bootstrap.connect(new InetSocketAddress("www.manning.com", 80));
        channelFuture.syncUninterruptibly();
    }
}
