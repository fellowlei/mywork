package com.mark.frame.queue;

import java.util.List;

/**
 * Created by lulei on 2016/3/2.
 */
public interface QI<T> {
    List<T> getTask();

    void doTask(T t);
}
