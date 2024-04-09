package hg.jh.luko6.common;

// 이것은 java.lang.RuntimeException을 확장하는 커스텀 예외입니다.
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
