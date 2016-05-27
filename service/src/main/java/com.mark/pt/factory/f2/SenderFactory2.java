package com.mark.pt.factory.f2;

import com.mark.pt.factory.MailSender;
import com.mark.pt.factory.Sender;
import com.mark.pt.factory.SmsSender;

/**
 * Created by lulei on 2016/5/25.
 */
public class SenderFactory2 {
    public static Sender produceMail() {
        return new MailSender();
    }

    public static Sender produceSms() {
        return new SmsSender();
    }

    public static void main(String[] args) {
        SenderFactory2.produceSms().send();
    }
}
