package com.example.tutorial.user.service;

import com.example.tutorial.user.entity.UserInfo;
import com.example.tutorial.user.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired private UserInfoRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> optionalUser = userRepository.findByUsername(username);

        return optionalUser
                .map(UserDetailsImpl::new) // 입력받은 username에 해당하는 사용자가 있다면, UserDetailsImpl 객체를 생성한다.
                .orElse(null); // 없다면 null을 반환한다. (인증 실패)
    }
}
