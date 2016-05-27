package com.mark.pt.strtegy;

/**
 * Created by lulei on 2016/5/26.
 */
public class Plus extends AbstractCalculator implements ICalculator {
    @Override
    public int calculate(String exp) {
        int[] arrayInt = split(exp, "\\+");
        return arrayInt[0] + arrayInt[1];
    }
}
