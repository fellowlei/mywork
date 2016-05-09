package com.mark.frame.th;

import java.util.concurrent.*;

/**
 * Created by lulei on 2016/3/3.
 */
public class FutureCallTest {

    public void calltest1() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> result = executorService.submit(task);
        executorService.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread start");

        try {
            System.out.println("task result: " + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("over");
    }

    public void calltest2() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        FutureTask futureTask = new FutureTask<Integer>(task);
        executorService.submit(futureTask);
        executorService.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread start");

        try {
            System.out.println("task result: " + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("over");
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("CallTest.call");
                Thread.sleep(3000);
                int sum = 0;
                for (int i = 0; i < 100; i++) {
                    sum = sum + i;
                }
                return sum;
            }
        });

        System.out.println("start");
        Thread.sleep(1000);
        System.out.println(future.isDone());
        Integer value = future.get();
        System.out.println(value);
        System.out.println(future.isDone());


        Future<Integer> future2 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("CallTest.call");
                Thread.sleep(3000);
                int sum = 0;
                for (int i = 0; i < 100; i++) {
                    sum = sum + i;
                }
                return sum;
            }
        });
        System.out.println("future2");
        System.out.println("value2: " + future2.get());

        Future<Integer> future3 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("CallTest.call");
                Thread.sleep(3000);
                int sum = 0;
                for (int i = 0; i < 100; i++) {
                    sum = sum + i;
                }
                return sum;
            }
        });
        System.out.println("future3");
        System.out.println("value3: " + future3.get());


    }

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("Task.call");
            Thread.sleep(3000);
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }

            return sum;
        }
    }
}
