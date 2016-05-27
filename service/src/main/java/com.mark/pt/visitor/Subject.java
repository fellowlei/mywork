package com.mark.pt.visitor;

/**
 * Created by lulei on 2016/5/27.
 */
public interface Subject {
    public void accept(Visitor visitor);

    public String getSubject();
}
