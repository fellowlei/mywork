package com.mark.frame.util.debug;

/**
 * Created by lulei on 2018/1/18.
 */
public class DebugBean {
    private String id;
    private String detail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "DebugBean{" +
                "id='" + id + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
