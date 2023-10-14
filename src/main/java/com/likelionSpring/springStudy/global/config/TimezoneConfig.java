package com.likelionSpring.springStudy.global.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class TimezoneConfig {
    //국가마다 시간대별로 해주는 class
    private static final String KST = "Asia/Seoul";

    //이런식으로 설정을 해주면 된다.
    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone(KST));
    }
}
