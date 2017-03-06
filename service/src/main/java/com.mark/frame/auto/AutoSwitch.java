package com.mark.frame.auto;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by lulei on 2017/3/2.
 */
public class AutoSwitch {
    public AtomicInteger fail = new AtomicInteger(0);
    public AtomicLong failTimeout = new AtomicLong(System.currentTimeMillis());
    public AtomicLong spendTime = new AtomicLong(System.currentTimeMillis());

    public AtomicInteger getFail() {
        return fail;
    }

    public void setFail(AtomicInteger fail) {
        this.fail = fail;
    }

    public AtomicLong getFailTimeout() {
        return failTimeout;
    }

    public void setFailTimeout(AtomicLong failTimeout) {
        this.failTimeout = failTimeout;
    }

    public AtomicLong getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(AtomicLong spendTime) {
        this.spendTime = spendTime;
    }
}
