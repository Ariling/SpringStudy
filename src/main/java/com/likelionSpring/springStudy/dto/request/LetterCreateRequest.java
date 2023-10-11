package com.likelionSpring.springStudy.dto.request;

import com.likelionSpring.springStudy.domain.entity.LetterEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LetterCreateRequest {
    private String title;
    private String content;

    public static LetterEntity toLetter(String title, String content) {
        return new LetterEntity(title, content);
    }
}
