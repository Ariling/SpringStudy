package com.likelionSpring.springStudy.service;

import com.likelionSpring.springStudy.domain.entity.LetterEntity;
import com.likelionSpring.springStudy.dto.request.LetterCreateRequest;
import com.likelionSpring.springStudy.dto.request.letter.LetterUpdateRequest;
import com.likelionSpring.springStudy.dto.response.letter.LetterGetResponse;
import com.likelionSpring.springStudy.repository.LetterJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LetterService {

private final LetterJpaRepository letterJpaRepository;

//생성할 때는 이거를 붙여줘야 함
@Transactional
public String create(LetterCreateRequest request) {
        LetterEntity letter = letterJpaRepository.save(LetterEntity.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build());
        return letter.getId().toString();
}
//@Transactional
//public String create(LetterCreateRequest request){
//        LetterEntity letter =  letterJpaRepository.save(LetterCreateRequest.toLetter(request.getTitle(), request.getContent()));
//        return letter.getId().toString();
//        }

        //업데이트를 할 때는 이런식으로 해주고 Jpa에는 기본적으로 제공해주는 메소드에 update는 없기 때문에
        //조회를 하고 업데이트를 하는 방향으로 가야한다.
        @Transactional
        public void updateLetter(Long letterId, LetterUpdateRequest request) {
                LetterEntity letter = letterJpaRepository.findByIdOrThrow(letterId);
                letter.updateTitle(request.title());
                letter.updateContent(request.content());
        }


public LetterGetResponse getById(Long id){
        return LetterGetResponse.of(letterJpaRepository.findByIdOrThrow(id));
        }
        }
