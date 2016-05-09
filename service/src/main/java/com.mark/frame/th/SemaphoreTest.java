package com.mark.frame.th;

import java.util.concurrent.Semaphore;

/**
 * Created by lulei on 2016/3/3.
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(5);

        for(int i=0; i<100; i++){
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(finalI);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        semaphore.release();
                    }

                }
            }).start();
        }

        for (int i = 0; i < 50; i++) {
            new Worker(i, semaphore).start();
        }
    }

    static class Worker extends Thread {

        private int num;
        private Semaphore semaphore;

        public Worker(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(semaphore.availablePermits() + "-" + semaphore.getQueueLength());
                System.out.println("name: " + num + " acquire");
                Thread.sleep(2000);
                System.out.println("name: " + num + " release--");
                semaphore.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
