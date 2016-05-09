package com.mark.frame.qredis;

import java.util.List;

/**
 * Created by lulei on 2016/3/2.
 */
public interface RQ<T> {
    List<T> getTask();

    void doTask(T t);
}
