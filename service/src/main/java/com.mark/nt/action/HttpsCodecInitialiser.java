package com.mark.nt.action;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/**
 * Created by lulei on 2016/3/15.
 */
public class HttpsCodecInitialiser extends ChannelInitializer<Channel> {
    private final boolean client;
    private final SSLContext context;

    public HttpsCodecInitialiser(boolean client, SSLContext context) {
        this.client = client;
        this.context = context;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        SSLEngine engine = context.createSSLEngine();
        engine.setUseClientMode(client);

        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addFirst("ssl",new SslHandler(engine));
        if(client){
            pipeline.addLast("codec",new HttpClientCodec());
        }else{
            pipeline.addLast("codec",new HttpServerCodec());
        }
    }
}
