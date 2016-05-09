package com.mark.frame.queue;

import java.util.List;

/**
 * Created by lulei on 2016/3/1.
 */
public interface QueueBase<T> {
    void doTask(T t);

    List<T> getTask();
}
