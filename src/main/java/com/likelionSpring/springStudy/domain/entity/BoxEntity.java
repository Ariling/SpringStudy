package com.likelionSpring.springStudy.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "box")
public class BoxEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //편지함 이름
    @Column(nullable = false, length = 40)
    private String name;

    //편지 연결(1대 다로 생각했는데 맞는지 모르겠어요 ㅠ)
    @OneToMany(mappedBy = "box")
    private List<LetterEntity> letters = new ArrayList<>();

    //Member와 1대 1 연결
    @OneToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private MemberEntity member;


}
