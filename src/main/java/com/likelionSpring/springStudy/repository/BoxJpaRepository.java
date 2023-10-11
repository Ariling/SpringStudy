package com.likelionSpring.springStudy.repository;


import com.likelionSpring.springStudy.domain.entity.BoxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoxJpaRepository extends JpaRepository<BoxEntity, Long> {

    default BoxEntity findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new IllegalArgumentException("해당 박스가 존재하지 않습니다."));
    }

    //code로 찾는 것인 거 같아 Id대신 code로 했습니다.
    default BoxEntity findByCodeOrThrow(String code) {
        return findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 편지함을 찾을 수 없습니다."));
    }

    //그냥 하면 에러가 뜨길래 다음과 같이 추가해서 사용했습니다.
    //final Optional<TodoEntity> original = repository.findById(entity.getId()); 이 부분을 보고 참고
    Optional<BoxEntity> findByCode(String code);
}
