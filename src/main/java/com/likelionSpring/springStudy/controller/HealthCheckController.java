package com.likelionSpring.springStudy.controller;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

//REST API를 위해 필요한 친구
@RestController
@RequestMapping("/api")
public class HealthCheckController {
    //접근제어자 - 제어자 - return - method 이름 - parameter - 그 다음 내용
    //테스트 용이지 이렇게 짜면 안된다... 매 번 이렇게 집어넣어서 하는 것보다는 추상화된 class를 만들고 활용하는 것이 더 중요!
    //Mapping은 Method에 따라서 이런식으로 만들어주면 된다. PostMapping, PutMapping 등등...
    //consumes, produces같은거를 할 수 있다. 저런식으로 MediaType을 해주면 내가 직접 일일이 입력을 안해도 된다.
    @GetMapping(value = "/health", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
//    public Map<String, String> healthCheck(){
//        Map<String, String> map = new HashMap<>();
//        map.put("status", "OK");
//        map.put("message", "Health Check is OK");
//        return map;
//    }
    //얘를 Return 하는 Class를 만들 것 (나중에...)
    public ResponseEntity<Map<String, String>> healthCheck(){
        Map<String, String> map = new HashMap<>();
        map.put("status", "OK");
        map.put("message", "Health Check is OK");
        return ResponseEntity.ok(map);
    }

}
