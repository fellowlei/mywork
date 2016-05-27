package com.mark.pt.observer;

/**
 * Created by lulei on 2016/5/27.
 */
public class MySubject extends AbstractSubject {
    @Override
    public void operation() {
        System.out.println("MySubject.operation");
        notifyObservers();
    }

    public static void main(String[] args) {
        Subject sub = new MySubject();
        sub.add(new Observer1());
        sub.add(new Observer2());
        sub.operation();
    }
}
