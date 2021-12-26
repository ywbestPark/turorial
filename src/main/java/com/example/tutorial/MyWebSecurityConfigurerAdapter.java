package com.example.tutorial;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@RequiredArgsConstructor
@Configuration
//@EnableJpaAuditing // 디폴트로 세팅되어 있는거 같음
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

    // 암호화 방식 빈(Bean) 생성
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        // static 하위 파일 목록(css, js, img) 인증 무시
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/assets/**", "/layout/**", "/*.html");
//        web.ignoring().antMatchers("/resources/**").anyRequest();
    }

//    @Bean
//    public AuthenticationSuccessHandler successHandler() {
//        return new CustomLoginSuccessHandler("/");//default로 이동할 url
//    }

    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher(){
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // http 관련 인증 설정
        /**
         * 1.세션 관련 설정
         *   1) 필요시 세션 생성
         *   2) session fixation 공격 방지를 위해 로그인 시 신규 세션 생성
         *   3) 유효하지 않은 세션으로 접속 시도 시 리다이렉션 페이지 설정
         *   4) 1명의 유저별로 동시 접속 가능한 세션 수 설정
         *   5) 동시 접속 가능한 세션 수 초과 시 접속 불가 설정
         *   6) 세션 만료시 리다이렉션 페이 설정
         *   7) 세션 레지스터리 설정
         */
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation().newSession()
//                .invalidSessionUrl("/invalidSession.html")
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredUrl("/sessionExpire.html")
                .sessionRegistry(sessionRegistry());

        /**
         * 2.h2-console에서 iframe을 사용하는데 이때 X-Frame-Options 에러가 발생하지 않도록 설정(sameorigin일 경우만 허용)
         */
        http.headers().frameOptions().sameOrigin();

        /**
         * 3.h2-console일때 csrf 토큰 체크 안하도록 설정, 기본으로 Get은 체크하지 않음, Post/Put일때는 꼭 csrf 토큰 사용 할 것
         */
        http.csrf().ignoringAntMatchers("/h2-console/**", "/logout/**");

        /**
         * 4.접근 허용 설정
         *   1) 누구나 접속 가능한 패스 설정
         *   2) 인증 + 특정 롤을 가진 사용자만 접속 가능 패스 설정
         *   3) 나머지는 인증 후 접속 가능토록 설정
         */
        http
                .authorizeRequests() // 접근에 대한 인증 설정
                    .antMatchers("/loginForm", "/joinForm", "/join", "/h2-console/**").permitAll() // 누구나 접근 허용
                    .antMatchers("/", "index").hasRole("USER") // USER, ADMIN만 접근 가능
                    .antMatchers("/user").hasRole("FAMILY") // ADMIN만 접근 가능
                    .anyRequest().authenticated();

        /**
         * 5.로그인 설정
         *   1) 로그인 페이지 설정
         *   2) 로그인 페이지에서 로그인을 위해 호출 하는 url 설정
         *   3) 나머지는 인증 후 접속 가능토록 설정
         *   4) 로그인 성공시 핸들러 설정
         *   5) 로그인 실패시 핸들서 설정
         *   6) 모두 로그 아웃에 접근 가능
         */
        http
                .formLogin() // 로그인에 관한 설정
                    .loginPage("/loginForm") // 로그인 페이지 링크
                    .loginProcessingUrl("/signin")
                    .successHandler((req, res, auth)->{
                        for (GrantedAuthority authority : auth.getAuthorities()){
                            System.out.println("Authority Information : "+authority.getAuthority());
                        }
                        System.out.println(auth.getName());
                        res.sendRedirect("/");
                    })
                    .failureHandler((req, res, exp)->{
                        String errMsg = "";
                        if(exp.getClass().isAssignableFrom(BadCredentialsException.class)){
                            errMsg = "Invalid username or password";
                        }else{
                            errMsg = "UnKnown error - "+exp.getMessage();
                        }
                        res.sendRedirect("/login");
                    })
                    .permitAll();

        http.oauth2Login();

        /**
         * 6.로그아웃 설정
         *   1) 로그아웃을 위해 호출 하는 주소
         *   2) 로그아웃 성공시 리다이렉트 주소
         *   3) 로그아웃 성공시 세션 무효화
         *   4) 로그아웃 성공시 쿠키 삭제
         *   5) 모두 로그 아웃에 접근 가능
         */
        http
                .logout()
                    .logoutUrl("/signout")
                    .logoutSuccessUrl("/loginForm")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "SESSION")
                    .permitAll();
    }
}