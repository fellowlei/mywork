package com.mark.gen;

/**
 * Created by lulei on 2016/3/28.
 */
public class genAdm {

    public static void main(String[] args) {
        int start = 6379;
        int start2 = 6379;
        for (int i = 0; i < 32; i++) {
            if (i % 16 == 0) start2 = 6379;
            if (i < 16) {
                String line = "172.20.115.37:" + start + "  172.28.65.156:" + start2 + "";
                System.out.println(line);
            } else if (i < 32) {
                String line = "172.20.115.37:" + start + "  172.28.65.158:" + start2 + "";
                System.out.println(line);
            }
            start++;
            start2++;
        }

        start = 6379;
        start2 = 6379;
        for (int i = 0; i < 32; i++) {
            if (i % 16 == 0) start2 = 6379;
            if (i < 16) {
                String line = "172.20.115.38:" + start + "  172.28.65.160:" + start2 + "";
                System.out.println(line);
            } else if (i < 32) {
                String line = "172.20.115.38:" + start + "  172.28.66.101:" + start2 + "";
                System.out.println(line);
            }
            start++;
            start2++;
        }
    }
}
