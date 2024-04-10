package hg.jh.luko6.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Aspect
@Component
public class BindingCheck {

    @Around("execution(* hg.jh.luko6.controller..*(..))")
    public Object validAdviceHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;
                if (bindingResult.hasErrors()) {
                    // 첫 번째 에러의 메시지를 가져옵니다.
                    String errorMessage = bindingResult.getFieldError().getDefaultMessage();
                    throw new ValidationException(errorMessage);
                }
            }
        }
        return joinPoint.proceed();
    }
}

//        if (result.hasErrors()) {
//// 검증 실패 시, 에러 메시지를 수집하여 반환
//Map<String, String> errors = new HashMap<>();
//            for (
//FieldError error : result.getFieldErrors()) {
//        errors.put(error.getField(), error.getDefaultMessage());
//        }
//
//        return ResponseEntity.badRequest().body(errors);
//        }