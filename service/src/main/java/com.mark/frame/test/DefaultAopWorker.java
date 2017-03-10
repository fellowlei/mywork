package com.mark.frame.test;

/**
 * Created by lulei on 2017/3/10.
 */
public class DefaultAopWorker implements  AopWorker {
    @Override
    public void start() {
        System.out.println("DefaultAopWorker.start");
    }
    public void init(){
        System.out.println("DefaultAopWorker.init");
    }

    public void end(){
        System.out.println("DefaultAopWorker.end");
    }

    @Override
    public void before(Object obj) {
        System.out.println("DefaultAopWorker.before");
        DefaultAopWorker worker = (DefaultAopWorker) obj;
        worker.init();

    }

    @Override
    public void after(Object obj) {
        System.out.println("DefaultAopWorker.after");
        DefaultAopWorker worker = (DefaultAopWorker) obj;
        worker.init();
    }
}
