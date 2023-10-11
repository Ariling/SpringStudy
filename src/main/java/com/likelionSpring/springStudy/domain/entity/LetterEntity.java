package com.likelionSpring.springStudy.domain.entity;

import com.likelionSpring.springStudy.domain.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "letter")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//얘가 박스에 대한 정보를 갖고 있어야 한다. 연관관계 주인이기 때문 1:N이니깐 그래서 box_id가 필요하다.
// 단방향 연관관계는 Box에서는 조회를 할 수가 없기 때문에 만약 필요하다면 Box에 List형태의 Letter가 존재하게 된다.
// 이 때 양방향 연관관계를 가졌다고 볼 수 있다. 하지만 이는 하지 않는게 낫다고는 하지만..
// 단방향에는 두 가지가 있는데 즉시로딩과 지연로딩이 있다.
public class LetterEntity extends BaseTimeEntity {
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

    @Builder
    //extends BaseTimeEntity를 다 붙여주기
    //Id가 안들어가는게 맞다.
    public LetterEntity(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
