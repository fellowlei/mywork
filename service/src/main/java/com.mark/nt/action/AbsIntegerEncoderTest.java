package com.mark.nt.action;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by lulei on 2016/3/15.
 */
public class AbsIntegerEncoderTest {

    @Test
    public void testEncoded() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 1; i < 10; i++) {
            buf.writeInt(i * -1);
        }

        EmbeddedChannel channel = new EmbeddedChannel(new AbsIntegerEncoder());

        Assert.assertTrue(channel.writeOutbound(buf));
        Assert.assertTrue(channel.finish());

        ByteBuf output = (ByteBuf) channel.readOutbound();
        for (int i = 1; i < 10; i++) {
            Assert.assertEquals(i, output.readInt());
        }

        Assert.assertFalse(output.isReadable());
        org.junit.Assert.assertNull(channel.readOutbound());
    }
}
