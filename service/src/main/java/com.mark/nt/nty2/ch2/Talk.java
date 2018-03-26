package com.mark.nt.nty2.ch2;

/**
 * Created by lulei on 2017/7/31.
 */
public class Talk {

    private String from;
    private String to;
    private String message;

    public Talk(String to, String from, String message) {
        this.to = to;
        this.from = from;
        this.message = message;
    }


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String encode(){
        return from + ":" + to + ":" + message;
    }

    @Override
    public String toString() {
        return from + ":" + to + ":" + message;
    }
}
