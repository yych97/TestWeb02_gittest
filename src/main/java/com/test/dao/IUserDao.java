package com.test.dao;

import com.test.pojo.User;

import java.util.List;
import java.util.Map;

public interface IUserDao {
    public int addUser(User user);
    public List<Map<String, Object>> queryUsersById(String id);
}
