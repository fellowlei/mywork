package com.mark.pt.build;

import com.mark.pt.factory.MailSender;
import com.mark.pt.factory.Sender;
import com.mark.pt.factory.SmsSender;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lulei on 2016/5/26.
 */
public class Builder {
    private List<Sender> list = new ArrayList<Sender>();

    public void produceMailSender(int count) {
        list.add(new MailSender());
    }

    public void produceSmsSender(int count) {
        list.add(new SmsSender());
    }

    public static void main(String[] args) {
        Builder builder = new Builder();
        builder.produceMailSender(10);
    }
}
