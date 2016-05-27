package com.mark.pt.visitor;

/**
 * Created by lulei on 2016/5/27.
 */
public class MyVisitor implements Visitor {
    @Override
    public void visit(Subject sub) {
        System.out.println("MyVisitor.visit" + sub.getSubject());
    }
}
