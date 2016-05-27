package com.mark.pt.factory;


/**
 * Created by lulei on 2016/5/25.
 */
public class SmsSender implements Sender {
    @Override
    public void send() {
        System.out.println("SmsSender.send");
    }
}
