package com.estsoft.springproject.user.config;

import com.estsoft.springproject.user.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

// 스프링 시큐리티 설정
@Configuration
public class WebSecurityConfiguration {
    private final UserDetailService userDetailService;

    public WebSecurityConfiguration(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    // 특정 요청은 스피링 시큐리티 설정을 타지 않도록 ignore 요청
    @Bean
    public WebSecurityCustomizer ignore() { // 1) 스프링 시큐리티 기능 비활성화
        return webSecurity -> webSecurity.ignoring()
                .requestMatchers(toH2Console()) // h2-console
                .requestMatchers("/static/**");
    }

    // 2) 특정 요청에 대한 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(
                        custom -> custom.requestMatchers("/login", "/signup", "/user").permitAll()
                                .anyRequest().authenticated()
                )
                // 3) 인증, 인가 설정
//                .requestMatchers("/login", "/signup", "/user").permitAll()
//                .requestMatchers("/api/external").hasRole("admin") // 인가
//                .anyRequest().authenticated()
                // 4) 폼 기반 로그인 설정
                .formLogin(custom -> custom.loginPage("/login")
                        .usernameParameter("email")  // 폼에서 이메일을 사용자명으로 사용
                        .passwordParameter("password")  // 비밀번호 필드
                        .defaultSuccessUrl("/articles", true))
//                .loginPage("/login")
//                .defaultSuccessUrl("/articles") // 로그인 성공했을 경우 리디렉션
                // 5) 로그아웃 설정
                .logout(custom -> custom.logoutSuccessUrl("/login")
                        .invalidateHttpSession(true))
//                .logoutSuccessUrl("/login")
//                .invalidateHttpSession(true)
                // 6) csrf 비활성화
//                .csrf(custom -> custom.disable())
//                .csrf().disable()
                .build();
    }

    // 7) 인증관리자 관련 설정
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) {
//        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailService)  // 8) 사용자 정보 서비스 설정
//                .passwordEncoder(bCryptPasswordEncoder)
//                .and()
//                .build();

    // 9) 패스워드 암호화 방식 (BCrptPasswordEncoder) 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
