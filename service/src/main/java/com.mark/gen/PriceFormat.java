package com.mark.gen;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Created by lulei on 2017/8/29.
 */
public class PriceFormat {
    /**
     * 把价格0去掉
     */
    public static String formatPrice(BigDecimal price){
        NumberFormat nf = NumberFormat.getInstance();
        return nf.format(price);
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     */
    public static String formatPrice(String price){
        if(price.indexOf(".") > 0){
            price = price.replaceAll("0+?$", "");//去掉多余的0
            price = price.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return price;
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis()/1000);
        NumberFormat nf = NumberFormat.getInstance();
        System.out.println(nf.format(new BigDecimal("10.00")));
        System.out.println(nf.format(new BigDecimal("10.10")));
        System.out.println(nf.format(new BigDecimal("10.010")));
        System.out.println(nf.format(new BigDecimal("11000.0010")));
        System.out.println(PriceFormat.formatPrice(new BigDecimal("11000.0010").toString()));
    }
}
