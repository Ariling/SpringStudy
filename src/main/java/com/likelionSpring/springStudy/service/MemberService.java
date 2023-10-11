package com.likelionSpring.springStudy.service;

import com.likelionSpring.springStudy.domain.entity.MemberEntity;
import com.likelionSpring.springStudy.dto.request.member.MemberSignInRequest;
import com.likelionSpring.springStudy.dto.response.member.MemberGetResponse;
import com.likelionSpring.springStudy.repository.BoxJpaRepository;
import com.likelionSpring.springStudy.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) //정보조회할때만 true로! 원래는 fault가 디폴트임
public class MemberService {
    //의존성 선언하고 생성자 만들어서 의존성 주입하고
    private final MemberJpaRepository memberJpaRepository;
    private final BoxJpaRepository boxJpaRepository;
    //데이터를 꺼내오기 위한 도구..

    //Repository랑 연결지어서 쉽게 할 수도 있다!
    public MemberGetResponse getById(Long id){
        return MemberGetResponse.of(memberJpaRepository.findByIdOrThrow(id));
    }


    //request로 들어가고, 저 TODO: 이런식으로 하면 음영처리가 된다.
    //builder()패턴을 이용해서 만드는 것 request하고 변수를 붙여주면 된다. method getter를 이용해서 데려온다.
    //TODO: 사실상 getter가 request.nickname이런식인 것! JAVA 17버전에서만 가능
    @Transactional
    public Long create(MemberSignInRequest request) {
        //TODO: create auth logic
        MemberEntity member = memberJpaRepository.save(
                MemberEntity.builder()
                        .nickname(request.nickname())
                        .username(request.username())
                        .build()
        );
        return member.getId();
    }

    @Transactional
    public void deleteById(Long id) {
        MemberEntity member = memberJpaRepository.findByIdOrThrow(id);
        member.softDelete();
        //레포지토리에 deleteById나 delete(entity)가 있는데 안 넣은 이유?
        //30일 동안 보관을 해야하는데 레포지토리의 저걸 활용하면 그냥 다 날려버리기 때문(hardDelte방식)
        //softDelete()라는 함수를 만들어 삭제한 것처럼 보이게 하는 것!
    }

    @Transactional
    public void recoverMemberInfo(Long id) {
        MemberEntity member = memberJpaRepository.findByIdOrThrow(id);
        member.recover();
    }


}

