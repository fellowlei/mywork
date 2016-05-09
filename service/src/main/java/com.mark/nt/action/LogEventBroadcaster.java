package com.mark.nt.action;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;

/**
 * Created by lulei on 2016/3/16.
 */
public class LogEventBroadcaster {
    private final EventLoopGroup group;
    private final Bootstrap bootstrap;
    private final File file;

    public LogEventBroadcaster(InetSocketAddress address, File file) {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioDatagramChannel.class).option(ChannelOption.SO_BROADCAST, true)
                .handler(new LogEventEncoder(address));
        this.file = file;
    }

    public void run() throws Exception {
        Channel ch = bootstrap.bind(0).syncUninterruptibly().channel();
        long pointer = 0;
        for (; ; ) {
            long len = file.length();
            if (len < pointer) {
                pointer = len;
            } else {
                RandomAccessFile raf = new RandomAccessFile(file, "r");
                raf.seek(pointer);
                String line;
                while ((line = raf.readLine()) != null) {
                    ch.write(new LogEvent(null, file.getAbsolutePath(), line, -1));
                }
                ch.flush();
                pointer = raf.getFilePointer();
                raf.close();
            }

            Thread.sleep(1000);
        }
    }

    public void stop() {
        group.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception {
        int port = 4096;
        String path = System.getProperty("user.dir") + "/log.txt";
        LogEventBroadcaster broadcaster = new LogEventBroadcaster(new InetSocketAddress("255.255.255.255", port), new File(path));
        try {
            broadcaster.run();
        } finally {
            broadcaster.stop();
        }
    }
}
