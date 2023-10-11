package com.likelionSpring.springStudy.controller;

import com.likelionSpring.springStudy.dto.request.member.MemberSignInRequest;
import com.likelionSpring.springStudy.dto.response.member.MemberGetResponse;
import com.likelionSpring.springStudy.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
    //ResponseEntity는 추상화 되어있는 것이 좋다. API에 다 들어갈 수 있게 만드는 것, 응답을 할 때 사용 들어갈 객체를 괄호안에 넣으면 return할때
    //Json으로 바꿔준다.
    //<>제네릭을 선언하는 것, 형을 선언하는 것 저기 안에 들어가는 건 Dto
    @GetMapping("/{member_Id}")
    public ResponseEntity<MemberGetResponse> getMember(@PathVariable("member_Id") Long memberId){
        //응답코드에 대한 method가 정해져 있다. accepted 203 ok 200 이런식으로! notFound는 404
        return ResponseEntity.ok(memberService.getById(memberId));
    }

    //요청이 들어오는 것
    //회원가입을 하는 것, RequestBody에 값이 필요한 경우에는 Annotation을 붙이고 이에 맞는 dto를 설정해준다.
    //아... get일때만 return저게 필요하네 post나 delete에서는 굳이 필요하지 않으니깐
    @PostMapping
    public ResponseEntity<Void> signIn(@RequestBody MemberSignInRequest request) {
        URI location = URI.create("/api/member/" + memberService.create(request));
        return ResponseEntity.created(location).build();
    }

    //삭제 관련
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> withdrawMembership(@PathVariable("memberId") Long memberId) {
        memberService.deleteById(memberId);
        return ResponseEntity.ok().build();
    }
    //Post가 꼭 생성만을 의미하지는 않는다. 실제로도 이 방법으로 쓰인다. PatchMapping을 사용해도 된다.
    @PostMapping("/{memberId}")
    public ResponseEntity<Void> recoverMembership(@PathVariable("memberId") Long memberId) {
        memberService.recoverMemberInfo(memberId);
        return ResponseEntity.ok().build();
    }
}
