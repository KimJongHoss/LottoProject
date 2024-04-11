package hg.jh.luko6.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
@Log4j2

public class ProductService {
//    각 항목을 소수점 세자리까지만 맵에 담기
    public float onlyThreeDecimalPlaces(long totalWinning, float productPrice){
        DecimalFormat df = new DecimalFormat("#.###");

        String logCabinValue = df.format(totalWinning / productPrice);
        float product = Float.parseFloat(logCabinValue);
        return product;
    }

}
