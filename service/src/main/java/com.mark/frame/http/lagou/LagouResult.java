package com.mark.frame.http.lagou;

/**
 * Created by lulei on 2016/4/29.
 */
public class LagouResult {
    private boolean success;

    private int code;

    private Content content;

    public void setSuccess(boolean success){
        this.success = success;
    }
    public boolean getSuccess(){
        return this.success;
    }
    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
    public void setContent(Content content){
        this.content = content;
    }
    public Content getContent(){
        return this.content;
    }
}
