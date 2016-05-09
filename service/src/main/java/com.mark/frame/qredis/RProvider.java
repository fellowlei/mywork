package com.mark.frame.qredis;

/**
 * Created by lulei on 2016/3/2.
 */
public interface RProvider<T> {
    void lpush(T t);

    String rpop();

    public Long llen();

}
