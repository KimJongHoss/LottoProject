package hg.jh.luko6.service;

import hg.jh.luko6.DTO.NumberDTO;
import hg.jh.luko6.entity.InputLotto;
import hg.jh.luko6.utility.NumberUtility;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class NumberCheckService {
    public NumberDTO getInputEntity(InputLotto entity) {//InputLotto를 NumberDTO로 변환하는 메소드
        NumberDTO dto = new NumberDTO();
        dto.setNum1(entity.getNum1());
        dto.setNum2(entity.getNum2());
        dto.setNum3(entity.getNum3());
        dto.setNum4(entity.getNum4());
        dto.setNum5(entity.getNum5());
        dto.setNum6(entity.getNum6());
        return dto;
    }

    public boolean processNumberDTO(NumberDTO dto) {
        // Null 체크
        if (dto.getNum1() == null || dto.getNum2() == null || dto.getNum3() == null ||
                dto.getNum4() == null || dto.getNum5() == null || dto.getNum6() == null) {
            return false;
        }

        // 숫자 여부 확인
        if (!NumberUtility.isNumeric(dto.getNum1().toString()) || !NumberUtility.isNumeric(dto.getNum2().toString()) ||
                !NumberUtility.isNumeric(dto.getNum3().toString()) || !NumberUtility.isNumeric(dto.getNum4().toString()) ||
                !NumberUtility.isNumeric(dto.getNum5().toString()) || !NumberUtility.isNumeric(dto.getNum6().toString())) {
            return false;
        }

        // 중복 체크
        if (!dto.checkUniqueNumber()) {
            return false;
        }

        // 범위 체크
        if (!isValidRange(dto.getNum1()) || !isValidRange(dto.getNum2()) || !isValidRange(dto.getNum3()) ||
                !isValidRange(dto.getNum4()) || !isValidRange(dto.getNum5()) || !isValidRange(dto.getNum6())) {
            return false;
        }

        return true;
    }

//    숫자의 범위 1~45 체크
    private boolean isValidRange(Integer number) {
        return number >= 1 && number <= 45;
    }

}

