package com.mark.frame.multi.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lulei on 2018/1/22.
 */
public class DefaultTaskWork extends TaskWorkFrame<Task> {
    public static final LinkedBlockingQueue<Task> taskQueue = new LinkedBlockingQueue<Task>(30000);
    private static final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    private static AtomicInteger idGen = new AtomicInteger();
    @Override
    public List<Task> getTaskList() {
        List<Task> list = new ArrayList<Task>();
        try {
            Task task = taskQueue.take();
            list.add(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void doTask(Task task) {
        task.action();
    }

    /**
     * 生成
     */
    public void produce(){
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<5; i++){
                    taskQueue.offer(new Task(idGen.incrementAndGet()));
                }
            }
        },1,1000,TimeUnit.MILLISECONDS);
    }

    /**
     * 消费
     */
    public void consume(){
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                doAllTask();
            }
        },1,1000,TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) {
        DefaultTaskWork defaultTaskWork = new DefaultTaskWork();
        defaultTaskWork.produce();
        defaultTaskWork.consume();
    }
}
