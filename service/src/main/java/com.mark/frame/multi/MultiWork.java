package com.mark.frame.multi;

import java.util.List;

/**
 * Created by lulei on 2015/12/30.
 */
public interface MultiWork<T> {
    /**
     * 获取任务列表
     * @return
     */
    List<T> getWorkList();

    /**
     * 开始批量做任务
     */
    void doMultiWork();

    /**
     * 做单个任务
     * @param t
     */
    void doSingleWork(T t);

    /**
     * 批量做任务前处理数据
     * @param t
     */
    void beforeWork(List<T> t);

    /**
     * 批量做任务后处理数据
     * @param t
     */
    void afterWork(List<T> t);

}