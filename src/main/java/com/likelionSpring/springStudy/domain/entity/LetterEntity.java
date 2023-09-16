package com.likelionSpring.springStudy.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "letter")
public class LetterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //작성자 1~10자
    @Column(nullable = false, length = 20)
    private String writer;
    //제목 1~50자
    @Column(nullable = false, length = 100)
    private String title;
    //내용
    @Column(nullable = false)
    private String content;
    //이미지.. 일단은 url만..
    @Column(nullable = false)
    private String imgUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "box_id")
    private BoxEntity box;


}
