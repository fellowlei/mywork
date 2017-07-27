package com.mark.nt.nty2.ch8;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by lulei on 2017/7/26.
 */
public class ServerBootStrapWithClient {
    public static void main(String[] args) {
        ServerBootstrap bootstrap =new ServerBootstrap();
        bootstrap.group(new NioEventLoopGroup(),new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new SimpleChannelInboundHandler<ByteBuf>() {
                    ChannelFuture channelFuture;
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        Bootstrap bootstrap =new Bootstrap();
                        bootstrap.channel(NioSocketChannel.class).handler(new SimpleChannelInboundHandler<ByteBuf>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                                System.out.println("received data");
                            }
                        });
                        bootstrap.group(ctx.channel().eventLoop());
                        channelFuture =  bootstrap.connect(new InetSocketAddress("www.manning.com", 80));
                    }

                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                             if(channelFuture.isDone()){

                             }
                    }
                });
        ChannelFuture future = bootstrap.bind(new InetSocketAddress(8080));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(channelFuture.isSuccess()){
                    System.out.println("server bound");
                }else{
                    System.out.println("bind attempt failed");
                    channelFuture.cause().printStackTrace();
                }
            }
        });
    }
}
