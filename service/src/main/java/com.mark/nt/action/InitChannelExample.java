package com.mark.nt.action;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.channel.socket.oio.OioDatagramChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;

/**
 * Created by lulei on 2016/3/15.
 */
public class InitChannelExample {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitialImpl());
    }


    static void udptest() {
        Bootstrap b = new Bootstrap();
        b.group(new OioEventLoopGroup()).channel(OioDatagramChannel.class)
                .handler(new SimpleChannelInboundHandler<DatagramPacket>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {

                    }
                });
        ChannelFuture f = b.bind(new InetSocketAddress(0));
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("channel bound");
                } else {
                    System.out.println("bound failed");
                    future.cause().printStackTrace();
                }
            }
        });
    }

    static void tcptest() {
        final AttributeKey<Integer> id = AttributeKey.valueOf("ID");

        Bootstrap b = new Bootstrap();
        b.group(new NioEventLoopGroup()).channel(NioSocketChannel.class)
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                        System.out.println("received data");
                        msg.clear();
                    }

                    @Override
                    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
                        Integer idValue = ctx.channel().attr(id).get();
                        System.out.println(idValue);
                    }
                });
        b.option(ChannelOption.SO_KEEPALIVE, true).option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
        b.attr(id, 123456);
        ChannelFuture f = b.connect("www.manning.com", 80);
        f.syncUninterruptibly();
    }

    static class ChannelInitialImpl extends ChannelInitializer<Channel> {

        @Override
        protected void initChannel(Channel ch) throws Exception {
            ch.pipeline().addLast(new HttpClientCodec()).addLast(new HttpObjectAggregator(Integer.MAX_VALUE));
        }
    }
}
