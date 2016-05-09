package com.mark.nt.nty.mi;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by lulei on 2016/3/25.
 */
public class IDGen {
    private static AtomicLong id = new AtomicLong();

    public static long getNextId(){
        return id.getAndIncrement();
    }
}
