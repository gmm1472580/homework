package com.itheima.service;

import com.itheima.domain.PageBean;
import com.itheima.domain.Param;
import com.itheima.domain.User;

import java.util.List;

public interface UserService {
    PageBean<User> findAll(Param param);
    User findByUsernameAndPassword(User user);
    void addUser(User user);
    void deleteUserById(int id);
    User findById(int id);
    void updateById(User user);
}
