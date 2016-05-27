package com.mark.pt.command;

/**
 * Created by lulei on 2016/5/27.
 */
public class MyCommand implements Command {

    private Receiver receiver;

    public MyCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void exe() {
        receiver.action();
    }
}
