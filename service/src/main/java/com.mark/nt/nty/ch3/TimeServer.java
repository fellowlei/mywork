package com.mark.nt.nty.ch3;

/**
 * Created by lulei on 2016/3/22.
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8888;
        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
        new Thread(timeServer, "aio-timerserver-001").start();
    }
}
