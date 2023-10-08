package com.likelionSpring.springStudy.service;

import com.likelionSpring.springStudy.domain.entity.LetterEntity;
import com.likelionSpring.springStudy.dto.request.LetterCreateRequest;
import com.likelionSpring.springStudy.repository.LetterJpaRepository;
import lombok.AllArgsConstructor;
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
    public String create(LetterCreateRequest request){
        LetterEntity letter =  letterJpaRepository.save(LetterCreateRequest.toLetter(request.getTitle(), request.getContent()));
        return letter.getId().toString();
    }
}
