package com.example.tutorial;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // Security 사용
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

    // 암호화 방식 빈(Bean) 생성
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) { // static 하위 파일 목록(css, js, img) 인증 무시
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // http 관련 인증 설정
        http.headers().frameOptions().sameOrigin();
        http
                .csrf()
                    .ignoringAntMatchers("/h2-console/**")
                .and()
                .authorizeRequests() // 접근에 대한 인증 설정
                    .antMatchers("/loginForm", "/joinForm", "/join").permitAll() // 누구나 접근 허용
                    .antMatchers("/", "index").hasRole("USER") // USER, ADMIN만 접근 가능
                    .antMatchers("/user").hasRole("FAMILY") // ADMIN만 접근 가능
                    .anyRequest().authenticated()
                //.anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
                .and()
                .formLogin() // 로그인에 관한 설정
                    .loginPage("/loginForm") // 로그인 페이지 링크
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/image?page=0&size=9&sortBy=createdDate-desc") // 로그인 성공 후 리다이렉트 주소
                    .permitAll()
                .and()
                .logout() // 로그아웃
                    .logoutSuccessUrl("/login") // 로그아웃 성공시 리다이렉트 주소
                    .invalidateHttpSession(true) // 세션 날리기
        ;
    }
}