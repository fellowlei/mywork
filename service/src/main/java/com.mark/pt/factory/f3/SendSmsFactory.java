package com.mark.pt.factory.f3;

import com.mark.pt.factory.Sender;
import com.mark.pt.factory.SmsSender;

/**
 * Created by lulei on 2016/5/25.
 */
public class SendSmsFactory implements Provider {
    @Override
    public Sender produce() {
        return new SmsSender();
    }

    public static void main(String[] args) {
        new SendSmsFactory().produce().send();
    }
}
