package com.mark.nt.nty.mi;

import java.io.Serializable;

/**
 * Created by lulei on 2016/3/25.
 */
public class MiResponse implements Serializable{
    private Long id;
    private Integer result;
    private String message;
    private String type;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
