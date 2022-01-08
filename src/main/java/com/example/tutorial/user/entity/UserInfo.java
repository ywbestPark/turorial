package com.example.tutorial.user.entity;

import com.example.tutorial.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DynamicInsert
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 658724990821020276L;

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

    // enabled / disabled
//    private boolean isEnabled;

    private String picture;

    public UserInfo update(String name, String picture){
        this.username = name;
        this.picture = picture;
        return this;
    }

//    // 계정 생성일
//    @CreationTimestamp private LocalDateTime createdDate;
}

