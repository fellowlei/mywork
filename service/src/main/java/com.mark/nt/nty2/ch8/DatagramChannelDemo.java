package com.mark.nt.nty2.ch8;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.oio.OioDatagramChannel;

import java.net.InetSocketAddress;

/**
 * Created by lulei on 2017/7/26.
 */
public class DatagramChannelDemo {
    public static void main(String[] args) {
        Bootstrap bootstrap =new Bootstrap();
        bootstrap.group(new OioEventLoopGroup()).channel(OioDatagramChannel.class)
                .handler(new SimpleChannelInboundHandler<DatagramPacket>() {

                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {

                    }
                });
        ChannelFuture future =bootstrap.bind(new InetSocketAddress(0));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(channelFuture.isSuccess()){
                    System.out.println("channel bound");
                } else{
                    System.out.println("bind failed");
                    channelFuture.cause().printStackTrace();
                }
            }
        });
    }
}
