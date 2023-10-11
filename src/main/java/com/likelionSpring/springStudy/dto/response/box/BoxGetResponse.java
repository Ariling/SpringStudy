package com.likelionSpring.springStudy.dto.response.box;

import com.likelionSpring.springStudy.domain.entity.BoxEntity;
import com.likelionSpring.springStudy.domain.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoxGetResponse {
    private String name;
    private MemberEntity member;

    // 이 부분은 box 정보를 데려오는 것이므로 BoxEntity Builder를 보고 참고해서 만들었습니다.
    public static BoxGetResponse of(BoxEntity box){
        return new BoxGetResponse(box.getName(), box.getMember());
    }
}
