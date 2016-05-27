package com.mark.pt.template;

/**
 * Created by lulei on 2016/5/27.
 */
public class Plus extends Abstractcalculator {
    @Override
    public int calculate(int num1, int num2) {
        return num1 + num2;
    }

    public static void main(String[] args) {
        String exp = "8+8";
        Abstractcalculator cal = new Plus();
        int result = cal.calculate(exp, "\\+");
        System.out.println(result);
    }
}
