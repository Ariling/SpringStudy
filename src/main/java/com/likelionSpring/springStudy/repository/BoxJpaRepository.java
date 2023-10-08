package com.likelionSpring.springStudy.repository;


import com.likelionSpring.springStudy.domain.entity.BoxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoxJpaRepository extends JpaRepository<BoxEntity, Long> {

}
