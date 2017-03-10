package com.mark.frame.test;

/**
 * Created by lulei on 2017/3/10.
 */
public class DefaultWorker implements Worker {
    @Override
    public void start() {
        System.out.println("DefaultWorker.start");
    }
}
