package com.likelionSpring.springStudy.service;

import com.likelionSpring.springStudy.domain.entity.MemberEntity;
import com.likelionSpring.springStudy.dto.response.member.MemberGetResponse;
import com.likelionSpring.springStudy.repository.MemberJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) //정보조회할때만 true로! 원래는 fault가 디폴트임
public class MemberService {
    //의존성 선언하고 생성자 만들어서 의존성 주입하고
    private final MemberJpaRepository memberJpaRepository;
    //데이터를 꺼내오기 위한 도구..

    //Repository랑 연결지어서 쉽게 할 수도 있다!
    public MemberGetResponse getById(Long id){
        return MemberGetResponse.of(memberJpaRepository.findByIdOrThrow(id));
    }


}
