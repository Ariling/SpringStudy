package com.likelionSpring.springStudy.domain.entity;

import com.likelionSpring.springStudy.repository.MemberJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberEntityTest {
    //구글에 인텔리제이 테스트라고 치면 나온대...

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @DisplayName("")
    @Test
    void test(){
        //given
        MemberEntity member = MemberEntity.builder()
                .username("mingle")
                .nickname("밍글")
                .password("user1234")
                .build();
        memberJpaRepository.save(member);
        //when
        MemberEntity findMember = memberJpaRepository.findById(1L).get();
        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
    }
}
