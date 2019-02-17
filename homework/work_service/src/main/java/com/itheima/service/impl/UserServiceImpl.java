package com.itheima.service.impl;

import com.itheima.domain.PageBean;
import com.itheima.domain.Param;
import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    public PageBean<User> findAll(Param param) {

        int currentPage=param.getCurrentPage();
        int rows=param.getRows();
        if (currentPage<=0){
            currentPage=1;
        }
        if (rows<=0){
            rows=5;
        }
        PageBean<User> pageBean = new PageBean<User>();
        pageBean.setRows(rows);
        int start=(currentPage-1)*rows;
        param.setStart(start);
        param.setRows(rows);
        int totalCount=userMapper.findTotalCount(param);
        pageBean.setTotalCount(totalCount);
        int totalPage= (int) Math.ceil(totalCount*1.0/rows);
        if (currentPage>totalPage){
            currentPage=totalPage;
        }
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalPage(totalPage);
        List<User> list=userMapper.findAll(param);
        pageBean.setList(list);


        return pageBean;
    }

    public User findByUsernameAndPassword(User user) {
        return userMapper.findByUsernameAndPassword(user);
    }

    public void addUser(User user) {
        userMapper.addUser(user);
    }

    public void deleteUserById(int id) {
        userMapper.deleteUserById(id);
    }

    public User findById(int id) {
        return userMapper.findById(id);
    }

    public void updateById(User user) {
        userMapper.updateById(user);
    }
}
