package com.mark.frame.multi.v2;

/**
 * Created by lulei on 2018/1/22.
 */
public class Task  {

    private Integer id;

    public Task(Integer id) {
        this.id = id;
    }

    public void action() {
        System.out.println(id);
    }
}
