package com.mark.nt.nty.ch12;

import com.mark.nt.nty.ch7.MarshallingCodecFactory;
import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.Marshaller;
import org.jboss.netty.buffer.ChannelBufferOutputStream;

import java.io.IOException;

/**
 * Created by lulei on 2016/3/24.
 */
public class MarshallingEncoder {
    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];
    private Marshaller marshaller;

    public MarshallingEncoder() {
//        this.marshaller = MarshallingCodecFactory.buildMarshallingEncoder();
    }

    public void encode(Object msg, ByteBuf out) throws IOException {
        int length = out.writerIndex();
        out.writeBytes(LENGTH_PLACEHOLDER);
//        ChannelBufferOutputStream outputStream = new ChannelBufferOutputStream();
//        marshaller.start(out);
        marshaller.writeObjectUnshared(msg);
        marshaller.flush();
        ;
        out.setInt(length, out.writerIndex() - length - 4);
    }
}
