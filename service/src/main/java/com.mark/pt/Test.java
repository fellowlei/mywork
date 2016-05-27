package com.mark.pt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by lulei on 2016/5/16.
 */
public class Test {

    public static void main(String[] args) {
        final Exchanger<List<Integer>> exchanger = new Exchanger<List<Integer>>();
        new Thread() {
            @Override
            public void run() {
                List<Integer> l = new ArrayList<Integer>(2);
                l.add(1);
                l.add(2);
                try {
                    l = exchanger.exchange(l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread1" + l);
            }
        }.start();


        new Thread() {
            @Override
            public void run() {
                List<Integer> l = new ArrayList<Integer>(2);
                l.add(3);
                l.add(4);
                try {
                    l = exchanger.exchange(l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread2" + l);
            }
        }.start();
    }

}
