package com.mark.nt.nty.ch2;

/**
 * Created by lulei on 2016/3/22.
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8888;
        MultiplexerTimerServer timerServer = new MultiplexerTimerServer(port);
        new Thread(timerServer, "nio-timeserver-001").start();

    }
}
