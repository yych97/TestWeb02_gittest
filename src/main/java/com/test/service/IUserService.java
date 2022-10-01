package com.test.service;

import com.test.pojo.User;

import java.util.List;
import java.util.Map;

public interface IUserService {
    public int addUser(User user);
    public List<Map<String, Object>> queryUsersById(String id);
}