package com.mark.pt.observer;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by lulei on 2016/5/27.
 */
public abstract class AbstractSubject implements Subject {
    private Vector<Observer> vector = new Vector<Observer>();

    @Override
    public void add(Observer observer) {
        vector.add(observer);
    }

    @Override
    public void del(Observer observer) {
        vector.remove(observer);
    }

    @Override
    public void notifyObservers() {
        Enumeration<Observer> elems = vector.elements();
        while (elems.hasMoreElements()) {
            elems.nextElement().update();
        }
    }

}
