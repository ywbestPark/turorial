package com.example.tutorial.user.service;

import com.example.tutorial.user.entity.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {

    private UserInfo userInfo;

    public UserDetailsImpl(UserInfo user) {
        this.userInfo = user;
    }

    /**
     * 해당 유저의 권한을 가져오는 메소드
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role : userInfo.getRole().split(",")){
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    /**
     * 비밀번호를 가지고 오는 메소드
     *
     * @return
     */
    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return userInfo.getUsername();
    }

    /**
     * 계정 만료 확인
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정 잠금 확인
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 계정 비밀번호 변경 확인
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 계정 활성화 확인
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return userInfo.isEnabled();
    }
}
