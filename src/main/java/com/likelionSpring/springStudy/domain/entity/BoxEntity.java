package com.likelionSpring.springStudy.domain.entity;

import com.likelionSpring.springStudy.domain.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "box")
public class BoxEntity extends BaseTimeEntity {

    private static final int DEFAULT_LETTER_LIMIT = 20;
    //pk를 노출시키는 것은 위험한 방식! 식별자를 쓰기보다는 code라는 필드를 만들어서 이걸로 조회를 하도록 설정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //편지함 이름
    @Column(nullable = false, length = 40)
    private String name;

    private int letterLimit = DEFAULT_LETTER_LIMIT;

    private String code;

    //편지 연결(1대 다로 생각했는데 맞는지 모르겠어요 ㅠ)
    //뭘로 선언되어있는지 알려주는 것! 필요한 경우가 많을 때 사용
    @OneToMany(mappedBy = "box")
    private List<LetterEntity> letters = new ArrayList<>();

    //Member와 1대 1 연결
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private MemberEntity member;

    @Builder
    public BoxEntity(String name, int letterLimit, String code, MemberEntity member) {
        validate(letterLimit);
        this.name = name;
        this.code = code;
        this.member = member;
    }

    private void validate(int letterLimit) {
        validateLetterLimit(letterLimit);
    }


    private void validateLetterLimit(int letterLimit) {
        if (letterLimit > DEFAULT_LETTER_LIMIT || letterLimit < 0) {
            throw new IllegalArgumentException("Invalid Letter Limit");
        }
    }


}
