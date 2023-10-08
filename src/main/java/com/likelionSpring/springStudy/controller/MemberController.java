package com.likelionSpring.springStudy.controller;

import com.likelionSpring.springStudy.dto.response.member.MemberGetResponse;
import com.likelionSpring.springStudy.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //알아서 json으로 바꿔주는 역할
@RequestMapping("/api/member")
@RequiredArgsConstructor //알아서 final로 정의한 변수들을 만들어준다.
public class MemberController {
        private final MemberService memberService;

        //생성자를 대신 만들어주는 것이 Required 어쩌구임
//        public MemberController(MemberService memberService){
//            this.memberService = memberService;
//        }
    //특정 사용자를 조회하는 API
    //ResponseEntity는 추상화 되어있는 것이 좋다. API에 다 들어갈 수 있게 만드는 것
    //<>제네릭을 선언하는 것, 형을 선언하는 것 저기 안에 들어가는 건 Dto
    @GetMapping("/{member_Id}")
    public ResponseEntity<MemberGetResponse> getMember(@PathVariable("member_Id") Long memberId){
        return ResponseEntity.ok(memberService.getById(memberId));
    }
}
