package com.mark.frame.call;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class KryoTool {

    public static <T> T clone(T obj) {
        Kryo kryo = new Kryo();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Output output = new Output(os);
        kryo.writeObject(output, obj);
        output.close();

        ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
        Input input = new Input(is);
        T result = (T) kryo.readObject(input, obj.getClass());
        input.close();
        return result;
    }

    public static String encode(Object obj) {
        if (obj == null) {
            return null;
        }
        Kryo kryo = new Kryo();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Output output = new Output(os);
        kryo.writeObject(output, obj);
        output.close();

        return new String(new Base64().encode(os.toByteArray()));
    }

    public static <T> T decode(String encode, Class<T> clazz) {
        if (StringUtils.isEmpty(encode)) {
            return null;
        }
        Kryo kryo = new Kryo();
        byte[] decode = new Base64().decode(encode);
        ByteArrayInputStream is = new ByteArrayInputStream(decode);
        Input input = new Input(is);
        T result = (T) kryo.readObject(input, clazz);
        input.close();
        return result;
    }

}
