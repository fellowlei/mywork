package com.mark.frame.multi.v2;

import java.util.List;

/**
 * Created by lulei on 2018/1/22.
 */
public interface TaskWork<T> {

    List<T> getTaskList();

    void doAllTask();

    void doTask(T t);
}
