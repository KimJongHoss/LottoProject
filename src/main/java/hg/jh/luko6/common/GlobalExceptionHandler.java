package hg.jh.luko6.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 커스텀 ValidationException을 처리하기 위한 핸들러
@ControllerAdvice
public class GlobalExceptionHandler {

    // 정의한 커스텀 ValidationException 클래스를 처리합니다.
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException ex) {
        // 에러 메시지만을 ResponseEntity의 바디로 설정합니다.
        return  ResponseEntity.badRequest().body(ex.getMessage());

//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST) // 400 상태 코드를 설정합니다.
//                .body(ex.getMessage()); // 예외 메시지만 바디에 포함합니다.
    }
}
