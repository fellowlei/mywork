package com.mark.frame.compare;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lulei on 2016/12/21.
 * 异步比对程序
 */
public class CompareManager {
    private static final Log log = LogFactory.getLog(CompareManager.class);
    private static CompareManager manager = new CompareManager();
    private BlockingQueue<ErrorTask> blockingQueue = new ArrayBlockingQueue<ErrorTask>(500);
    private ExecutorService executorService = null;

    private CompareManager() {
        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(5);
            Thread thread = new CompareThread();
            thread.start();
        }
    }

    public static CompareManager getIntance() {
        return manager;
    }

    public void addErrorTask(ErrorTask errorTask) {
        blockingQueue.add(errorTask);
    }

    public class CompareThread extends Thread {
        private boolean run = true;

        @Override
        public void run() {
            log.error("###compare thread start...");
            while (run) {
                try {
                    ErrorTask errorTask = blockingQueue.take();
                    if (errorTask.getRequest() instanceof Request) {
                        DemoCompareTask compareTask = new DemoCompareTask(errorTask);
                        compareTask.setRepeatTime(2);
                        executorService.submit(compareTask);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            log.error("###compare thread end...");
        }

        public void shutdown() {
            this.interrupt();
            this.run = false;
        }
    }
}
