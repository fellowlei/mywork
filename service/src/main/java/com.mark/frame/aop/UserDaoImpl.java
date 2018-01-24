package com.mark.frame.aop;

/**
 * Created by lulei on 2018/1/24.
 */
public class UserDaoImpl implements UserDao {
    public int addUser() {
        System.out.println("UserDaoImpl.addUser");
        return 666;
    }

    public void updateUser() {
        System.out.println("UserDaoImpl.updateUser");
    }

    public void deleteUser() {
        System.out.println("UserDaoImpl.deleteUser");
    }

    public void findUser() {
        System.out.println("UserDaoImpl.findUser");
    }
}
