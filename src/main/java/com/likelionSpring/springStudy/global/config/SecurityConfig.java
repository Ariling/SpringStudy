package com.likelionSpring.springStudy.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity //이걸 붙여줘야함
//설정을 해둬야 띄울 수 있다.
public class SecurityConfig {
    /*
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.jetbrains:annotations:24.0.0'
	implementation 'org.springframework.boot:spring-boot-starter-security'
	얘네 추가해주기
    * */

    @Bean //Bean으로 등록하기
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //filter를 먼저 걸어두고 필요한 필터들을 쌓아놓는 형식
        //예시 : jwt 검증
        //csrf란? 사용자가 요청을 보내면 http에 값을 살짝 다르게 해서 보내는 것
        //얘를 끄는 이유 : 웹 서버에 csrf에 대응을 안 해놓으면 json에서 Key-Value로 되어있어서 위험성이 사실상 없기 때문
        //Rest API 설계했을 떄 꺼놔도 된다고 공식문서에도 나와있다.
        return http.csrf().disable()
                .authorizeHttpRequests()
                //어떤 요청이든 다 받겠다는 뜻!
                .anyRequest().permitAll()
                //마지막에 이런식으로 해주면 된다.
                .and().build();
    }

    //Cors 설정
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedOriginPatterns("*")
                        .allowedMethods("*");
                //지금은 다 허용했지만 예를 들어 get하고 post만 했다면 allowedMethods에 해당 method만 걸어두고
                //Origins도 원하는 대로 조정을 해서 넣으면 된다.
            }
        };
    }


}
