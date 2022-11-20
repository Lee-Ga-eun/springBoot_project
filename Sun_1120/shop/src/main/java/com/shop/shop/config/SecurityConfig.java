package com.shop.shop.config;

import com.shop.shop.service.NftMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// SecurityConfig의 filterChain 설정 -> MemberService의 loadUserByUsername -> header.html에서 Authority 메소드

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    // Spring Security 5.7.0-M2 부터 기존 WebSecurityConfigureAdapter 방식에서 SecurityFilterChain 으로 변경 권장
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 인증이 필요한 요청폼 설정
        http.formLogin()
                .loginPage("/member/login")
                //.loginPage("/nft_member/lgn.html")
                .loginProcessingUrl("/member/login")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .failureUrl("/member/login/error")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
        ;

        //인증 실패시 라우팅
        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
        ;

        http
                .csrf().disable(); //form을 submit했을 때 403 에러 해결

        return http.build();
    }

//    @Bean
//    protected SecurityFilterChain configure(HttpSecurity http) throws Exception{
//        http.cors().and().csrf().disable();
//        return http.build();
//    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and();
//        http.csrf().disable();
//    }



}