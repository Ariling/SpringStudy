package com.likelionSpring.springStudy.dto.response.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.likelionSpring.springStudy.domain.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberGetResponse {
    //JsonProperty("이름 넣으면 여기각 Key값이 된다.")
//    @JsonProperty("멤버이름")
    private String username;
    private String nickname;

    //얘가 중요한 파트인가봐 이런식으로 데려온대!
    public static MemberGetResponse of(MemberEntity member){
        return new MemberGetResponse(member.getUsername(), member.getNickname());
    }
}
