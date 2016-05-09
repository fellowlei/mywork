package com.mark.nt.action;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;

/**
 * Created by lulei on 2016/3/16.
 */
public class EventLogMonitor {
    private final EventLoopGroup group;
    private final Bootstrap bootstrap;

    public EventLogMonitor(InetSocketAddress address) {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioDatagramChannel.class).option(ChannelOption.SO_BROADCAST, true)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new LogEventDecoder());
                        pipeline.addLast(new LogEventHandler());
                    }
                }).localAddress(address);
    }

    public Channel bind(){
        return bootstrap.bind().syncUninterruptibly().channel();
    }

    public void stop(){
        group.shutdownGracefully();
    }

    public static void main(String[] args) {
        EventLogMonitor monitor = new EventLogMonitor(new InetSocketAddress(4096));
        try {
            Channel channel = monitor.bind();
            System.out.println("log event monitor runing");
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            monitor.stop();
        }
    }
}
