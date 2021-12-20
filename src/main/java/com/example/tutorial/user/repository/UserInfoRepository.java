package com.example.tutorial.user.repository;


import com.example.tutorial.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    // username을 가지고 User 정보를 가져올 수 있게 메소드 생성
    Optional<UserInfo> findByUsername(String username);
}
