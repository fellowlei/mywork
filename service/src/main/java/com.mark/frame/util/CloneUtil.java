package com.mark.frame.util;

import com.alibaba.fastjson.JSON;
import com.mark.domain.User;

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
        User user2 = (User) deepClone2(user);
        user2.setName("steve");
        user2.getSubUser().setName("steve2");
        System.out.println(user);
        System.out.println(user2);
    }

    public static Object deepClone(Object obj) throws Exception {
        // 对象写到流里
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(obj);

        // 从流里读出来
        ByteArrayInputStream bi =new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        return oi.readObject();
    }

    public static <T extends Serializable> T clone3(T obj){

        T clonedObj = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            clonedObj = (T) ois.readObject();
            ois.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return clonedObj;
    }

    public static Object deepClone2(Object obj) throws Exception {
        String jsonString = JSON.toJSONString(obj);
        Object object = JSON.parseObject(jsonString, obj.getClass());
        return object;
    }


    public static void clone1(){
        User user =new User(1,"mark","pw1");
        String jsonString = JSON.toJSONString(user);
        User user2 = JSON.parseObject(jsonString,User.class);
        user2.setName("steve");
        System.out.println(user);
        System.out.println(user2);
    }
}
