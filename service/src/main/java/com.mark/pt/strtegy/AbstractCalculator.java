package com.mark.pt.strtegy;

/**
 * Created by lulei on 2016/5/26.
 */
public class AbstractCalculator {
    public int[] split(String exp, String opt) {
        String[] array = exp.split(opt);
        int[] arrayInt = new int[2];
        arrayInt[0] = Integer.parseInt(array[0]);
        arrayInt[1] = Integer.parseInt(array[1]);
        return arrayInt;
    }
}
