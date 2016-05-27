package com.mark.pt.mediator;

/**
 * Created by lulei on 2016/5/27.
 */
public class User1 extends User {

    public User1(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void work() {
        System.out.println("User1.work");
    }
}
