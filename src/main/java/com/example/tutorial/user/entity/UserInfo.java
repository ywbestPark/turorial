package com.example.tutorial.user.entity;

import com.example.tutorial.entity.BaseEntity;
import com.example.tutorial.user.dto.UserInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
@DynamicInsert
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo extends BaseEntity{

    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 사용자명
    @Column(unique=true)
    private String username;

    // 비밀번호
    private String password;

    // 이메일
    private String email;

    // 권한
    private String role;

    private String picture;

    public UserInfo update(String name, String picture){
        this.username = name;
        this.picture = picture;
        return this;
    }

    public UserInfo updateInfo(boolean passwordChange, UserInfoDTO userInfoDTO) {
        if(passwordChange){
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            this.password = bCryptPasswordEncoder.encode(userInfoDTO.getPassword());
        }

        this.username = userInfoDTO.getUsername();
        this.email = userInfoDTO.getEmail();
        this.role = userInfoDTO.getRole();
        this.setEnable(userInfoDTO.isEnable());

        return  this;
    }
}

