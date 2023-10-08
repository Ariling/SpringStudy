package com.likelionSpring.springStudy.controller;

import com.likelionSpring.springStudy.dto.request.LetterCreateRequest;
import com.likelionSpring.springStudy.service.LetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/letter")
@RequiredArgsConstructor
public class LetterController {

    private final LetterService letterService;
    @PostMapping
    public ResponseEntity<Void> sendLetter(@RequestBody LetterCreateRequest request){
        String letterId =  letterService.create(request);
        URI location = URI.create("/api/letter/" + letterId);
        //location이 들어가야 된다. responseBody에는 들어가는게 없어서 Void로 한거
        return ResponseEntity.created(location).build();
    }

}
