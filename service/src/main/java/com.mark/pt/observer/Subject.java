package com.mark.pt.observer;

/**
 * Created by lulei on 2016/5/27.
 */
public interface Subject {
    public void add(Observer observer);

    public void del(Observer observer);

    public void notifyObservers();

    public void operation();
}
