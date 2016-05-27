package com.mark.pt.mediator;

/**
 * Created by lulei on 2016/5/27.
 */
public class User2 extends User {

    public User2(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void work() {
        System.out.println("User2.work");
    }
}
