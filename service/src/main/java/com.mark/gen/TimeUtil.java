package com.mark.gen;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lulei on 2017/4/7.
 */
public class TimeUtil {
    public static void main(String[] args) {
        Long time = 1491451279 * 1000L;
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        System.out.println(sdf.format(date));
    }
}
