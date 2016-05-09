package com.mark.frame.th;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by lulei on 2016/3/3.
 */
public class CyclibBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {
            @Override
            public void run() {
                System.out.println("after all");
            }
        });
        for(int i=0; i<4; i++){
            new Writer(cyclicBarrier).start();
        }
        System.out.println("main over");
    }

    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;
        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("thread: " + Thread.currentThread() + " start");
            try {
                Thread.sleep(5000);
                System.out.println("thread: " + Thread.currentThread() + " write over");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("thread: " + Thread.currentThread() + " end");
        }
    }
}
