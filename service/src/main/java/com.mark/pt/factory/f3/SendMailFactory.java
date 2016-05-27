package com.mark.pt.factory.f3;

import com.mark.pt.factory.MailSender;
import com.mark.pt.factory.Sender;

/**
 * Created by lulei on 2016/5/25.
 */
public class SendMailFactory implements Provider {
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
