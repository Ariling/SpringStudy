package com.likelionSpring.springStudy.repository;

import com.likelionSpring.springStudy.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberEntity,Long> {
}

