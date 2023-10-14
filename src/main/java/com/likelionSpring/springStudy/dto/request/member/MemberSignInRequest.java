package com.likelionSpring.springStudy.dto.request.member;

public record MemberSignInRequest(
        //record란?
        //원래는 class로 만들고 진행을 했었음 LetterCreateRequest참고!
        //위를 좀 더 편하게 만들어주는 것, class인데 이미 선언을 parameter형식으로 해주고 제어자가 필요가 없다!
        //record 특징 ? 기존은 getter,setter,data,constructor 넣어줬어야 했는데 얘는 그냥 다 들어가 있어서 별도로 만들 필요 X
        String username,
        String password,
        String nickname
) {
}
