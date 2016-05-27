package com.mark.pt.prototype;

import java.io.*;

/**
 * Created by lulei on 2016/5/26.
 */
public class Prototype implements Cloneable, Serializable {

    private String string;


    public Object clone() throws CloneNotSupportedException {
        Prototype proto = (Prototype) super.clone();
        return proto;
    }

    public Object deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }

    private static class SerializableObject implements Serializable {

    }
}
