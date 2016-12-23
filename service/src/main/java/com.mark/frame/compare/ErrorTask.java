package com.mark.frame.compare;

/**
 * Created by lulei on 2016/12/21.
 */
public class ErrorTask<T> {
    private int times;
    private String info;
    private T request;

    public ErrorTask(T request) {
        this.request = request;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getRequest() {
        return request;
    }

    public void setRequest(T request) {
        this.request = request;
    }
}
