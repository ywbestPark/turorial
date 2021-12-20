package com.example.tutorial.user.entity;

import com.example.tutorial.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo extends BaseTimeEntity {
    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
    private boolean isEnabled;

//    // 계정 생성일
//    @CreationTimestamp private LocalDateTime createdDate;
}

