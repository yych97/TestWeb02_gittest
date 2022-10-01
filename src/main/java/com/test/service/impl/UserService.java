package com.test.service.impl;

import com.test.dao.IUserDao;
import com.test.dao.impl.UserDao;
import com.test.pojo.User;
import com.test.service.IUserService;

import java.util.List;
import java.util.Map;

public class UserService implements IUserService {

    private IUserDao userDao=new UserDao();
    @Override
    public int addUser(User user) {
        //1.验证手机

        //2.验证邮箱

        //3.调用dao层做添加
        return userDao.addUser(user);
    }

    @Override
    public List<Map<String, Object>> queryUsersById(String id) {
        return userDao.queryUsersById(id);
    }

}