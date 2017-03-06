package com.mark.frame.auto;

import com.mark.frame.auto.test.Hello;
import com.mark.frame.auto.test.OneHello;
import com.mark.frame.auto.test.TwoHello;

/**
 * Created by lulei on 2017/3/6.
 */
public class AutoSwitchTest {

    public static void main(String[] args) {
        Hello h1 = new OneHello();
        Hello h2 = new TwoHello();
        for(int i=0; i< 100; i++){
            Hello proxy = AutoSwitchInvocation.genAutoSwitch(h1, h2);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            proxy.sayHello();
        }

    }
}
