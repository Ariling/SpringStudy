package com.likelionSpring.springStudy.dto.response.member;

import com.likelionSpring.springStudy.domain.entity.LetterEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LetterGetResponse {
    private String title;
    private String content;

    public static LetterGetResponse of(LetterEntity letter){
        return new LetterGetResponse(letter.getTitle(),letter.getContent());
    }
}
