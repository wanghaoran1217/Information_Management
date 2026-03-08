package com.tjl.dao;

import com.tjl.bean.User;

import java.sql.SQLException;

public interface UserDao {
    //-1 登录失败  1 老板登录  2 员工登录
    int login(User user);

    boolean insert(User user);

    boolean delete(String uname) throws SQLException;

    boolean update(User user);

    User select(String uname);
}
