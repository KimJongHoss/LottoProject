package hg.jh.luko6.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class NumberDTO {
    @NotNull(message = "숫자를 입력해주세요")
    @Min(value = 1, message = "1 이상의 숫자를 입력해주세요")
    @Max(value = 45, message = "45 이하의 숫자를 입력해주세요")
    private Integer num1;
    @NotNull(message = "숫자를 입력해주세요")
    @Min(value = 1, message = "1 이상의 숫자를 입력해주세요")
    @Max(value = 45, message = "45 이하의 숫자를 입력해주세요")
    private Integer num2;
    @NotNull(message = "숫자를 입력해주세요")
    @Min(value = 1, message = "1 이상의 숫자를 입력해주세요")
    @Max(value = 45, message = "45 이하의 숫자를 입력해주세요")
    private Integer num3;
    @NotNull(message = "숫자를 입력해주세요")
    @Min(value = 1, message = "1 이상의 숫자를 입력해주세요")
    @Max(value = 45, message = "45 이하의 숫자를 입력해주세요")
    private Integer num4;
    @NotNull(message = "숫자를 입력해주세요")
    @Min(value = 1, message = "1 이상의 숫자를 입력해주세요")
    @Max(value = 45, message = "45 이하의 숫자를 입력해주세요")
    private Integer num5;
    @NotNull(message = "숫자를 입력해주세요")
    @Min(value = 1, message = "1 이상의 숫자를 입력해주세요")
    @Max(value = 45, message = "45 이하의 숫자를 입력해주세요")
    private Integer num6;

    public boolean checkUniqueNumber(){
        Set<Integer> numberSet = new HashSet<>();
        numberSet.add(num1);
        numberSet.add(num2);
        numberSet.add(num3);
        numberSet.add(num4);
        numberSet.add(num5);
        numberSet.add(num6);

        return numberSet.size() == 6;
    }

}
