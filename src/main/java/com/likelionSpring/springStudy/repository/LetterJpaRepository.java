package com.likelionSpring.springStudy.repository;

import com.likelionSpring.springStudy.domain.entity.LetterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterJpaRepository extends JpaRepository<LetterEntity, Long> {
    default LetterEntity findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 편지를 찾을 수 없습니다."));
    }
}
