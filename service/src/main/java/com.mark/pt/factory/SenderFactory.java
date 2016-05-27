package com.mark.pt.factory;

/**
 * Created by lulei on 2016/5/25.
 */
public class SenderFactory {
    public Sender produce(String type) {
        if ("mail".equals(type)) {
            return new MailSender();
        } else if ("sms".equals(type)) {
            return new SmsSender();
        } else {
            System.out.println("input type error:");
            return null;
        }
    }

    public static void main(String[] args) {
        SenderFactory factory = new SenderFactory();
        factory.produce("sms").send();
    }
}
