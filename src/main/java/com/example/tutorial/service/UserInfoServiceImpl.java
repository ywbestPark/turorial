package com.example.tutorial.service;

import com.example.tutorial.user.dto.UserInfoDTO;
import com.example.tutorial.user.entity.UserInfo;
import com.example.tutorial.user.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.JDBCException;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public UserInfo save(UserInfoDTO userInfoDTO) throws JDBCException {
        return userInfoRepository.save(userInfoDTO.toEntity());
    }

    @Override
    public UserInfo getUserById(Long id) {
        return userInfoRepository.getById(id);
    }

    @Override
    public UserInfo saveOrUpdate(UserInfoDTO userInfoDTO)  throws JDBCException {
        UserInfo userInfo = userInfoRepository.findByUsername(userInfoDTO.getUsername())
                .map(entity -> entity.updateInfo(userInfoDTO.isPasswordChange(), userInfoDTO))
                .orElse(userInfoDTO.toEntity());
                userInfoRepository.save(userInfo);

        return userInfo;
    }

    @Override
    public void delete(Long id) {
        userInfoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no data id=" + id));
        userInfoRepository.deleteById(id);
    }
}
