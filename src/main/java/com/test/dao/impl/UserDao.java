package com.test.dao.impl;

import com.test.dao.IUserDao;
import com.test.pojo.User;
import com.test.util.DbHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDao implements IUserDao {
    @Override
    public int addUser(User user) {
        //添加用户到数据库
        String sql="insert into users values(?,?,?)";
        List<Object> paramList=new ArrayList<Object>();
        paramList.add(user.getId());
        paramList.add(user.getName());
        paramList.add(user.getAge());

        DbHelper dh =new DbHelper();
        return dh.excecuteUpdate(sql, paramList);
    }

    @Override
    public List<Map<String, Object>> queryUsersById(String id) {
        String sql = "select * from users where id = ?";
        List<Object> paramList=new ArrayList<Object>();
        paramList.add(id);

        DbHelper dh = new DbHelper();
        return dh.executeQuery(sql, paramList);
    }
}
