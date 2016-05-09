package com.mark.nt.nty.ch7;

import com.mark.nt.nty.ch5.SubReqClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by lulei on 2016/3/23.
 */
public class SubReqClient {

    public void connect(int port,String host) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        try{
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY,true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(MarshallingCodecFactory.buildMarshallingDecoder());
                    ch.pipeline().addLast(MarshallingCodecFactory.buildMarshallingEncoder());
                    ch.pipeline().addLast(new SubReqClientHandler());
                }
            });

            ChannelFuture f = b.connect(host,port).sync();
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new SubReqClient().connect(8888,"localhost");
    }
}
