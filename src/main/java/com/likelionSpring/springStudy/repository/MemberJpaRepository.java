package com.likelionSpring.springStudy.repository;

import com.likelionSpring.springStudy.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberJpaRepository extends JpaRepository<MemberEntity,Long> {

    default MemberEntity findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 회원을 찾을 수 없습니다."));
    }
//        //findById가 옵션이고 얘는 null값을 가질 수 있기 때문
    //Exception을 처리할 Class가 필요한데

    @Query("select m from MemberEntity m where m.isDeleted = false")
    //얘에 해당하는 애들만 가져오게 하는 것
    List<MemberEntity> findAllNotDeleted();


    //Query가 나갈 때 해당하는 조건을 한 방에 처리할 때 Modifying이라는 Annotation을 쓴다.
    //Service - 영속성 컨텍스트 - DB
    @Modifying
    @Query("delete from MemberEntity m where m.isDeleted = true and m.deletedAt < now()")
    void deleteExpiredMember();
}

