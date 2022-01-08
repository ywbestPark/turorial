package com.example.tutorial.service;

import com.example.tutorial.user.entity.UserInfo;

import java.util.List;

public interface UserInfoService {
    List<UserInfo> getUserList();
    UserInfo save(UserInfo userInfo);
    UserInfo getUserById(Long id);
    void update(UserInfo userInfo);
    void delete(Long id);
}
