package com.likelionSpring.springStudy.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//Configuration이라고 붙이면 설정 Class로 사용할 수 있다.
//EnableJpaAuditing은 스프링 빈에 등록이 되어있어 있을 때 사용 가능 Configuration을 사용하면!
// 왜 굳이? 설정 Class를 빼면 내부에 코드가 없어져 가독성이 높아지기 때문
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
}
