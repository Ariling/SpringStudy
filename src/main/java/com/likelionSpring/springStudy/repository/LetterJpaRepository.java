package com.likelionSpring.springStudy.repository;

import com.likelionSpring.springStudy.domain.entity.LetterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterJpaRepository extends JpaRepository<LetterEntity, Long> {

}
