package com.example.tutorial.service;

import com.example.tutorial.user.entity.UserInfo;
import com.example.tutorial.user.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;

    @Override
    public List<UserInfo> getUserList() {
        return userInfoRepository.findAll();
    }

    @Override
    public UserInfo save(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    @Override
    public UserInfo getUserById(Long id) {
        return userInfoRepository.getById(id);
    }

    @Override
    public void update(UserInfo userInfo) {
        UserInfo userInfoNew = null;
        Optional<UserInfo> optionalUserInfo = userInfoRepository.findById(userInfo.getId());
        if(optionalUserInfo.isPresent()){
            userInfoNew = userInfoRepository.save(userInfo);
        }else{
            //no data error 리턴
        }
    }

    @Override
    public void delete(Long id) {
        userInfoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no data id=" + id));
        userInfoRepository.deleteById(id);
    }
}
