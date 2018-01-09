package com.mark.frame.util;

import com.alibaba.com.caucho.hessian.io.HessianInput;
import com.alibaba.com.caucho.hessian.io.HessianOutput;
import com.alibaba.fastjson.JSON;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.mark.domain.User;
import org.msgpack.MessagePack;

import java.io.*;

/**
 * Created by lulei on 2017/12/8.
 */
public class CloneUtil {
    public static void main(String[] args) throws Exception {
        test();
    }

    public static  void test() throws Exception {
        User user =new User(1,"mark","pw1");
        User subUser =new User(2,"mark2","pw2");
        user.setSubUser(subUser);
//        User user2 = (User) deepClone(user);
        User user2 = (User) cloneByKryo(user);
//        User user2 = (User) cloneByMsgpack(user);
        user2.setName("steve");
        user2.getSubUser().setName("steve2");
        System.out.println(user);
        System.out.println(user2);
    }

    public static Object cloneByJava(Object obj) throws Exception {
        // 对象写到流里
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(obj);

        // 从流里读出来
        ByteArrayInputStream bi =new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        return oi.readObject();
    }




    // msgpack
    public static Object cloneByMsgpack(Object obj) throws Exception {
        try {
            MessagePack pack = new MessagePack();
            pack.register(obj.getClass());
            // 序列化
            byte[] bytes = pack.write(obj);
            // 反序列化
            Object result = pack.read(bytes, obj.getClass());
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // kryo
    public static Object cloneByKryo(Object obj) throws Exception {
        Kryo kryo = new Kryo();
        Object result = kryo.copy(obj);
        return result;
    }

    // kryo
    public static Object cloenByKryo2(Object obj) throws Exception {
        Kryo kryo = new Kryo();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Output output = new Output(os);
        kryo.writeObject(output,obj);
        output.close();

        ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
        Input input = new Input(is);
        Object result = kryo.readObject(input, obj.getClass());
        return result;
    }


    // json
    public static Object cloneJson(Object obj){
        String jsonString = JSON.toJSONString(obj);
        return JSON.parseObject(jsonString,obj.getClass());
    }



    // hessian
    private static Object cloneHessian(Object obj) throws IOException {
        // serialize
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(os);
        ho.writeObject(obj);

        // deserialize
        ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
        HessianInput hi = new HessianInput(is);
        return hi.readObject();
    }
}
