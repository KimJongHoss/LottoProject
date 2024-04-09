package hg.jh.luko6.DTO;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class NumberDTO {

    private Integer num1;

    private Integer num2;

    private Integer num3;

    private Integer num4;

    private Integer num5;

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
