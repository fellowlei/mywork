package com.mark.service;

import com.mark.domain.User;
import com.mark.frame.FastJsonUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by lulei on 2016/3/11.
 */
public class MybatisTest {

    public SqlSession getSession() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();
        return session;
    }

    public void select() throws IOException {
        SqlSession session = getSession();
        try {
            User param = new User();
            param.setId(1);
            User user = session.selectOne("user.select", param);
            System.out.println(FastJsonUtils.toJson(user));
        } finally {
            session.close();
        }
    }

    public void selectAll() throws IOException {
        SqlSession session = getSession();
        try {
            List<User> users = session.selectList("user.selectAll");
            System.out.println(FastJsonUtils.toJson(users));
        } finally {
            session.close();
        }
    }

    public void add() throws IOException {
        SqlSession session = getSession();
        try {
            User user5 = new User(5, "user5", "pass5");
            int result = session.insert("user.insert", user5);
            session.commit();
            System.out.println("insert result: " + result);
        } finally {
            session.close();
        }
    }

    public void delete() throws IOException {
        SqlSession session = getSession();
        try {
            User user = new User();
            user.setId(5);
            Integer result = session.delete("user.delete", user);
            session.commit();
            System.out.println("delete result: " + result);
        } finally {
            session.close();
        }
    }

    public static void main(String[] args) throws IOException {
        new MybatisTest().selectAll();

    }


}
