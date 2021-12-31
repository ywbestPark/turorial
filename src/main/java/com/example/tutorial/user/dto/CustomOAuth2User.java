package com.example.tutorial.user.dto;

import com.example.tutorial.user.entity.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private OAuth2User oauth2User;
    private UserInfo userInfo;

    public CustomOAuth2User(OAuth2User oauth2User, UserInfo userInfo) {
        this.oauth2User = oauth2User;
        this.userInfo = userInfo;
    }

    @Override
    public Map<String, Object> getAttributes() {
//        return oauth2User.getAttributes();
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return oauth2User.getAuthorities();
        return null;
    }

    @Override
    public String getName() {
        return userInfo.getUsername();
    }

    public String getEmail() {
        return userInfo.getEmail();
    }

    public String getPicture() {
        return userInfo.getPicture();
    }

    @Override
    public String toString() {
        return "CustomOAuth2User{" +
//                "oauth2User=" + oauth2User +
                ", userInfo=" + userInfo +
                '}';
    }
}
