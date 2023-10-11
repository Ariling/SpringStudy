package com.likelionSpring.springStudy.dto.request.member;

public record MemberSignInRequest(
        String username,
        String password,
        String nickname
) {
}
