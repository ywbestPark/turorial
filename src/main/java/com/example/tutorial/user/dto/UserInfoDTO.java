package com.example.tutorial.user.dto;

import com.example.tutorial.user.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO implements Serializable {

    private static final long serialVersionUID = 658724990821020276L;

    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
    private String picture;
    private boolean isEnable;
    private boolean passwordChange;

    public UserInfoDTO update(String name, String picture){
        this.username = name;
        this.picture = picture;
        return this;
    }

    public UserInfo toEntity() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        UserInfo userInfo =  UserInfo.builder()
                            .username(this.username)
                            .password(bCryptPasswordEncoder.encode(this.password))
                            .email(this.email)
                            .role(this.role)
                            .picture(this.picture)
                            .build();
        userInfo.setEnable(isEnable);

        return  userInfo;
    }
}

