package com.mark.frame.queue;

import java.util.List;

/**
 * Created by lulei on 2016/3/1.
 */
public interface QueueBaseI<T> {
    public List<T> getTask();

    void doTask(T t);

}
