package com.likelionSpring.springStudy.service;

import com.likelionSpring.springStudy.dto.response.box.BoxGetResponse;
import com.likelionSpring.springStudy.repository.BoxJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoxService {
    private final BoxJpaRepository boxJpaRepository;

    public BoxGetResponse getByCode(String code){
        return BoxGetResponse.of(boxJpaRepository.findByCodeOrThrow(code));
    }
}
