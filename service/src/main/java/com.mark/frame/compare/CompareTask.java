package com.mark.frame.compare;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by lulei on 2016/12/21.
 */
public abstract class CompareTask<T> implements Runnable {
    private static final Log log = LogFactory.getLog(CompareTask.class);
    protected ErrorTask<T> errorTask;
    private int repeatTime = 0;

    public CompareTask(ErrorTask<T> errorTask) {
        this.errorTask = errorTask;
    }

    public void setErrorTask(ErrorTask<T> errorTask) {
        this.errorTask = errorTask;
    }

    public void setRepeatTime(int repeatTime) {
        this.repeatTime = repeatTime;
    }

    public abstract boolean compare(T request);

    @Override
    public void run() {
        if (!compare(errorTask.getRequest())) {
            if (errorTask.getTimes() == repeatTime) {
                // compare error
                log.error(errorTask.getInfo());
                log.error("#compare failed");
            } else {
                errorTask.setTimes(errorTask.getTimes() + 1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CompareManager.getIntance().addErrorTask(errorTask);
            }
        } else {
            // compare success
            log.error("#compare success");
        }
    }


}
