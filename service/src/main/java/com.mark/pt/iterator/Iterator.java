package com.mark.pt.iterator;

/**
 * Created by lulei on 2016/5/27.
 */
public interface Iterator {
    public Object previous();

    public Object next();

    public boolean hasNext();

    public Object first();
}
