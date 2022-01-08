package com.example.tutorial.service;

import com.example.tutorial.user.dto.UserInfoDTO;
import com.example.tutorial.user.entity.UserInfo;
import org.hibernate.JDBCException;

import java.util.List;

public interface UserInfoService{
    List<UserInfo> getUserList();
    UserInfo save(UserInfoDTO userInfoDTO) throws JDBCException;
    UserInfo getUserById(Long id);
    UserInfo saveOrUpdate(UserInfoDTO userInfoDTO)  throws JDBCException;
    void delete(Long id);
}
