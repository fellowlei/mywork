package com.mark.frame.http.core.v2;

import java.io.IOException;
import java.util.Map;

/**
 * Created by lulei on 2017/3/14.
 */
public class LoginHttpUtil {
    public static void login(String name, String password) throws IOException {
        String loginParam = "username=" + name + "&" + "password=" + password;
        SessionHttpUtil.loginPost("http://test.com/login.action", loginParam);
        System.out.println("login success");
    }

    public static String post(String url, String param) throws IOException {
        String result = SessionHttpUtil.loginPost(url, param);
        System.out.println(result);
        return result;
    }

    public static String post(String url, Map<String,String> paramMap) throws IOException {
        String result = SessionHttpUtil.loginPost(url, paramMap);
//        System.out.println(result);
        return result;
    }

    public static String get(String url) throws IOException {
        String result = SessionHttpUtil.get(url);
//        System.out.println(result);
        return result;
    }

    public static String get(String url,Map<String,String> headerMap) throws IOException {
        String result = SessionHttpUtil.get(url,headerMap);
//        System.out.println(result);
        return result;
    }

    public static void main(String[] args) throws IOException {
        login("mark", "pass");
        get("http://test.com/display");
        post("http://test.com/manager", "action=show");
    }
}
