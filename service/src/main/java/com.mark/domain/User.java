package com.mark.domain;

import java.io.Serializable;

/**
 * Created by lulei on 2016/3/11.
 */
public class User implements Serializable{
    private Integer id;
    private String name;
    private String password;
    private User subUser;

    public User(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getSubUser() {
        return subUser;
    }

    public void setSubUser(User subUser) {
        this.subUser = subUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", subUser=" + subUser +
                '}';
    }
}
