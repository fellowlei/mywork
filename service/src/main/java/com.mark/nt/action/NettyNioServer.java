package com.mark.nt.action;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * Created by lulei on 2016/3/14.
 */
public class NettyNioServer {
    public void server(int port) {
        final ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi!\r\n", CharsetUtil.UTF_8));

        EventLoopGroup group = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port)).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            ctx.writeAndFlush(buf.duplicate()).addListener(ChannelFutureListener.CLOSE);
                        }
                    });
                }
            });

            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            group.shutdownGracefully();
        }


    }

    public static void main(String[] args) {
//        ByteBuf directBuf = Unpooled.directBuffer(16);
//        if(!directBuf.hasArray()){
//            int len = directBuf.readByte();
//            byte[] arr = new byte[len];
//            directBuf.getBytes(0,arr);
//        }


//        CompositeByteBuf compBuf = Unpooled.compositeBuffer();
//        ByteBuf heapBuf = Unpooled.buffer(8);
//        ByteBuf directBuf = Unpooled.directBuffer(16);
//
//        compBuf.addComponents(heapBuf, directBuf);
//
//        compBuf.removeComponent(0);
//
//        Iterator<ByteBuf> iter = compBuf.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next().toString());
//        }
//
//        if (!compBuf.hasArray()) {
//            int len = compBuf.readableBytes();
//            byte[] arr = new byte[len];
//            compBuf.getBytes(0, arr);
//        }
//
//
//        ByteBuf buf = Unpooled.buffer(16);
//        for (int i = 0; i < 16; i++) {
//            buf.writeByte(i + 1);
//        }
//
//        for (int i = 0; i < buf.capacity(); i++) {
//            System.out.println(buf.getByte(i));
//        }

//        ByteBuf buf = Unpooled.buffer(16);
//        while(buf.isReadable()){
//            System.out.println(buf.readByte());
//        }

//        Random random = new Random();
//        ByteBuf buf = Unpooled.buffer(16);
//        while(buf.writableBytes() >= 4){
//            buf.writeInt(random.nextInt());
//        }

//        Charset utf8 = Charset.forName("UTF-8");
//
//        ByteBuf buf = Unpooled.copiedBuffer("netty in action rocks!", utf8);
//
//        ByteBuf sliced = buf.slice(0, 14);
//        ByteBuf copy = buf.copy(0, 14);
//
//        System.out.println(buf.toString(utf8));
//        System.out.println(sliced.toString(utf8));
//        System.out.println(copy.toString(utf8));

        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(group).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(8088)).childHandler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ByteBufAllocator alloc = ch.alloc();
//                ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
//                    @Override
//                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//                        ByteBufAllocator alloc2 = ctx.alloc();
////                        ctx.writeAndFlush()
//                    }
//                });

                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        Channel channel = ctx.channel();
                        channel.write(Unpooled.copiedBuffer("netty in action", CharsetUtil.UTF_8));
                        ChannelPipeline pipeline = ctx.pipeline();
                        pipeline.write(Unpooled.copiedBuffer("netty in action", CharsetUtil.UTF_8));
                    }
                });
            }
        });
    }
}
