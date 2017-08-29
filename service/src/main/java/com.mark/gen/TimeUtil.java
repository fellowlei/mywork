package com.mark.gen;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by lulei on 2017/4/7.
 */
public class TimeUtil {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis()/1000);
        NumberFormat nf = NumberFormat.getInstance();
        System.out.println(nf.format(new BigDecimal("10.00")));
        System.out.println(nf.format(new BigDecimal("10.10")));
        System.out.println(nf.format(new BigDecimal("10.010")));
        System.out.println(nf.format(new BigDecimal("11000.0010")));
        System.out.println(PriceFormat.formatPrice(new BigDecimal("11000.0010").toString()));

    }

    public static void showTime(long time){
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        System.out.println(sdf.format(date));
    }
    public static void showNow(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        System.out.println(sdf.format(date));
    }

    public static void test(){
        Map<String,String> map =new HashMap<String, String>();
        map.put("1","a");
        map.put("2","b");
        map.put("3","c");
        System.out.println(map);
        Iterator it = map.values().iterator();
        while(it.hasNext()){
            System.out.println(it.next());
            it.remove();
        }
        System.out.println(map);
    }
}
