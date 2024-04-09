package hg.jh.luko6.utility;

import java.util.regex.Pattern;

public class NumberUtility {
    public static boolean isNumeric(String str) {
        // 정규표현식을 사용하여 숫자인지 확인
        return Pattern.matches("\\d+", str);
    }
}
