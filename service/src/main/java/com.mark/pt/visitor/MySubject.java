package com.mark.pt.visitor;

/**
 * Created by lulei on 2016/5/27.
 */
public class MySubject implements Subject {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getSubject() {
        return "love";
    }

    public static void main(String[] args) {
        Visitor visitor = new MyVisitor();
        Subject sub = new MySubject();
        sub.accept(visitor);
    }
}
