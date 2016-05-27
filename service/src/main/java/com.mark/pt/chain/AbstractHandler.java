package com.mark.pt.chain;

/**
 * Created by lulei on 2016/5/27.
 */
public  abstract class AbstractHandler {
    private Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
