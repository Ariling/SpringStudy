package com.likelionSpring.springStudy.controller;

import com.likelionSpring.springStudy.dto.response.box.BoxGetResponse;
import com.likelionSpring.springStudy.service.BoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/box")
public class BoxController {

    private final BoxService boxService;

    //box 조회 API
    @GetMapping("/{box_code}")
    public ResponseEntity<BoxGetResponse> getBox(@PathVariable("box_code") String code){
        return ResponseEntity.ok(boxService.getByCode(code));
    }

}
