package com.likelionSpring.springStudy.controller;

import com.likelionSpring.springStudy.dto.request.box.BoxCreateRequest;
import com.likelionSpring.springStudy.dto.response.box.BoxGetResponse;
import com.likelionSpring.springStudy.service.BoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/box")
public class BoxController {

    private final BoxService boxService;

    @PostMapping
    public ResponseEntity<Void> createLetterBox(@RequestBody BoxCreateRequest request, Long memberId) {
        URI location = URI.create("/api/box/" + boxService.create(request, memberId));
        return ResponseEntity.created(location).build();
    }

    //box 조회 API
    @GetMapping("/{box_code}")
    public ResponseEntity<BoxGetResponse> getBox(@PathVariable("box_code") String code){
        return ResponseEntity.ok(boxService.getByCode(code));
    }

}
