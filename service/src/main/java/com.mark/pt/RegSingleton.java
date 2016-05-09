package com.mark.pt;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lulei on 2016/4/18.
 */
public class RegSingleton {

    private static Map<String, RegSingleton> map = new HashMap<String, RegSingleton>();

    static {
        RegSingleton singleton = new RegSingleton();
        map.put(singleton.getClass().getName(), singleton);
    }

    private RegSingleton() {
    }

    public static RegSingleton getInstance(String name) {
        if (name == null) {
            name = "RegSingleton";
        }
        if (map.get(name) == null) {
            try {
                map.put(name, (RegSingleton) Class.forName(name).newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map.get(name);
    }

    public void method1() {
        System.out.println("RegSingleton.method1");
    }
}
