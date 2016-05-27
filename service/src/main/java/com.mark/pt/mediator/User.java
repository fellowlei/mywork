package com.mark.pt.mediator;

/**
 * Created by lulei on 2016/5/27.
 */
public abstract class User {
    private Mediator mediator;

    public User(Mediator mediator) {
        this.mediator = mediator;
    }

    public Mediator getMediator() {
        return mediator;
    }

    public abstract  void work();
}
