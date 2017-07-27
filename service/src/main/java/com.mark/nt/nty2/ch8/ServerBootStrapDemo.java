package com.mark.nt.nty2.ch8;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by lulei on 2017/7/26.
 */
public class ServerBootStrapDemo {
    public static void main(String[] args) {
        NioEventLoopGroup group =new NioEventLoopGroup();
        ServerBootstrap bootstrap =new ServerBootstrap();
        bootstrap.group(group).channel(NioServerSocketChannel.class)
                .childHandler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                        System.out.println("received data");
                    }
                });
        ChannelFuture channelFuture =  bootstrap.bind(new InetSocketAddress(8080));
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(channelFuture.isSuccess()){
                    System.out.println("server bound");
                }else{
                    System.out.println("bound attempt failed");
                    channelFuture.cause().printStackTrace();
                }
            }
        });
    }
}
