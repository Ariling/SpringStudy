package com.likelionSpring.springStudy.service;

import com.likelionSpring.springStudy.domain.entity.BoxEntity;
import com.likelionSpring.springStudy.domain.entity.MemberEntity;
import com.likelionSpring.springStudy.dto.request.box.BoxCreateRequest;
import com.likelionSpring.springStudy.dto.response.box.BoxGetResponse;
import com.likelionSpring.springStudy.dto.response.letter.LetterGetResponse;
import com.likelionSpring.springStudy.repository.BoxJpaRepository;
import com.likelionSpring.springStudy.repository.LetterJpaRepository;
import com.likelionSpring.springStudy.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoxService {
    private final BoxJpaRepository boxJpaRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final LetterJpaRepository letterJpaRepository;

    public BoxGetResponse getByCode(String code){
        BoxEntity box = boxJpaRepository.findByCodeOrThrow(code);
        List<LetterGetResponse> letters = letterJpaRepository.findAllByBox(box)
                //List로 이루어질 것임
                .stream()
                //하나씩 Mapping해줌
                .map(LetterGetResponse::of)
                //parameter를 하나씩 넣고 실행하게 되는 것, 그거를 람다식으로 바꾼 것
                .collect(Collectors.toList());
        return BoxGetResponse.of(box,letters);

    }




    @Transactional
    public String create(BoxCreateRequest request, Long memberId) {
        BoxEntity box = boxJpaRepository.save(
                BoxEntity.builder()
                        .name(request.name())
                        //UUID코드를 넣어준 것
                        .code(generateCode())
                        .member(findMemberById(memberId))
                        .build()
        );
        return box.getCode();
    }

    //UUID를 생성시켜주는 것 (겹칠 확률을 아예 없다고 봐도 무방)
    //subString으로 slice를 해준 것!
    private String generateCode() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    private MemberEntity findMemberById(Long memberId) {
        return memberJpaRepository.findByIdOrThrow(memberId);
    }
}
