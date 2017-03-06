package com.mark.frame.worker;

import org.apache.commons.lang3.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lulei on 2017/3/6.
 */
public class AbstractLifeCycle {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    protected volatile boolean running = false;

    public void start() {
        if (running) {
            throw new IllegalStateException(ClassUtils.getShortClassName(getClass()) + "is started, don't restart it.");
        }
        running = true;
        doStart();
        log.info(ClassUtils.getShortClassName(getClass()) + " started");
    }

    protected void doStart() {

    }

    public void stop() {
        if (!running) {
            return;
        }
        running = false;
        doStop();
        log.info(ClassUtils.getShortClassName(getClass()) + " stopped");
    }

    protected void doStop() {

    }
}
