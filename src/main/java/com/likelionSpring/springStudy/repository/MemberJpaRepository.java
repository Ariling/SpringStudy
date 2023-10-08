package com.likelionSpring.springStudy.repository;

import com.likelionSpring.springStudy.domain.entity.MemberEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberEntity,Long> {

    default MemberEntity findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 회원을 찾을 수 없습니다."));
    }
//        //findById가 옵션이고 얘는 null값을 가질 수 있기 때문
}

