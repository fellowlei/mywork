package com.mark.nt.nty.ch2;

/**
 * Created by lulei on 2016/3/22.
 */
public class TimeClient {
    public static void main(String[] args) {
        new Thread(new TimeClientHandle("localhost",8888),"timeclient-001").start();
    }
}
