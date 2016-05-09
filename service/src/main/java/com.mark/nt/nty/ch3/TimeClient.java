package com.mark.nt.nty.ch3;

/**
 * Created by lulei on 2016/3/22.
 */
public class TimeClient {
    public static void main(String[] args) {
        new Thread(new AsyncTimeClientHandler("localhost",8888),"aio-timeclient-001").start();
    }
}
