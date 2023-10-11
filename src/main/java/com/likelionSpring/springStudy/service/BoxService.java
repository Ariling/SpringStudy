package com.likelionSpring.springStudy.service;

import com.likelionSpring.springStudy.domain.entity.BoxEntity;
import com.likelionSpring.springStudy.domain.entity.MemberEntity;
import com.likelionSpring.springStudy.dto.request.box.BoxCreateRequest;
import com.likelionSpring.springStudy.dto.response.box.BoxGetResponse;
import com.likelionSpring.springStudy.repository.BoxJpaRepository;
import com.likelionSpring.springStudy.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoxService {
    private final BoxJpaRepository boxJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    public BoxGetResponse getByCode(String code){
        return BoxGetResponse.of(boxJpaRepository.findByCodeOrThrow(code));
    }

    @Transactional
    public String create(BoxCreateRequest request, Long memberId) {
        BoxEntity box = boxJpaRepository.save(
                BoxEntity.builder()
                        .name(request.name())
                        .code(generateCode())
                        .member(findMemberById(memberId))
                        .build()
        );
        return box.getCode();
    }

    private String generateCode() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    private MemberEntity findMemberById(Long memberId) {
        return memberJpaRepository.findByIdOrThrow(memberId);
    }
}
