package com.itheima.mapper;

import com.itheima.domain.Param;
import com.itheima.domain.User;

import java.util.List;

public interface UserMapper {
    List<User> findAll(Param param);
    User findByUsernameAndPassword(User user);
    void addUser(User user);
    void deleteUserById(int id);
    User findById(int id);
    void updateById(User user);
    Integer findTotalCount(Param param);

}
