package com.example.tutorial.user.dto;

import com.example.tutorial.user.entity.UserInfo;
import lombok.Getter;

import java.io.Serializable;

/**
 * 직렬화 기능을 가진 User클래스
 */
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(UserInfo userInfo){
        this.name = userInfo.getUsername();
        this.email = userInfo.getEmail();
        this.picture = userInfo.getPicture();
    }
}