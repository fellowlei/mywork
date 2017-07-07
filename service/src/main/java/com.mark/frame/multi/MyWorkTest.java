package com.mark.frame.multi;


import com.mark.frame.multi.domain.TestTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lulei on 2015/12/25.
 */
public class MyWorkTest extends MultiWorkImpl<TestTask> {
    private static ExecutorService executorService = Executors.newFixedThreadPool(100);

    @Override
    public List<TestTask> getWorkList() {
        List<TestTask> taskList = new ArrayList<TestTask>();
        for (int i = 0; i < 2000; i++) {
            taskList.add(new TestTask(1, 2, 2));
        }
        return taskList;
    }

    @Override
    public void afterWork(List<TestTask> t) {
        check(t);
    }


    @Override
    public void doSingleWork(TestTask task) {
        Integer result = task.getC() + task.getB() + task.getA();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }
        task.setResult(result);
    }

    public void singleThread() {
        long startTime = System.currentTimeMillis();
        List<TestTask> taskList = getWorkList();
        for (TestTask task : taskList) {
            doSingleWork(task);
        }
        check(taskList);
        long costTime = System.currentTimeMillis() - startTime;
        System.out.println("MyWork.singleThread. cost:" + costTime);

    }

    public void multiThead() {
        long startTime = System.currentTimeMillis();
        new MyWorkTest().doMultiWork();
        long costTime = System.currentTimeMillis() - startTime;
        System.out.println("MyWork.multiThread. cost:" + costTime);
    }

    public void check(List<TestTask> taskList) {
        for (TestTask task : taskList) {
            if (!task.getResult().equals(5)) {
                System.out.println("MyWork.check" + task.getResult());
            }
        }
        System.out.println(taskList.size() + ":" + taskList.get(taskList.size() - 1).getResult());
    }


    public static void main(String[] args) {
//            new MyWork().singleThread();
//        for (int i = 0; i < 20; i++) {
//            new MyWorkTest().multiThead();
//        }
        new MyWorkTest().multiTest();
    }

    public void multiTest() {
        for(int i=0; i<10; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    new MyWorkTest().multiThead();
                }
            });
        }
        System.out.println("end");
        executorService.shutdown();
    }

}
