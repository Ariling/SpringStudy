package com.likelionSpring.springStudy.global;

import com.likelionSpring.springStudy.global.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //Exceeption일시 이게 작동하게 된다. 실패에 대한 response를 해줄 필요가 없다.
    //이게 없으면 Controller에서 조건부를 달아서 해야하는데 이게 있다면
    //Controller에는 성공만 남겨주면 된다!
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
    //잘못된 파라미터나 변수가 들어갔을 때 Illegal을 터뜨리고 기본적으로 400대가 나온다.

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().build();
    }
    //이렇게 활용을 해주면 된다. Exception도 이런식으로 처리를 해줄 수가 있다.
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Void> handleBusinessException(BusinessException e){
        return ResponseEntity.internalServerError().build();
    }
}
