package hg.jh.luko6.controller;


import hg.jh.luko6.entity.InputLotto;
import hg.jh.luko6.entity.Lotto;
import hg.jh.luko6.entity.OutputLotto;
import hg.jh.luko6.service.LottoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Log4j2
@RequiredArgsConstructor
public class LottoController {

    private final LottoService lottoService;

    @GetMapping("/")
    public String index(){
        log.info("index로 갑니당");
        return "index";
    }

//    @PostMapping("/getLotto")
//    public String getLotto(InputLotto inputLotto, Model model){
//
//        List<OutputLotto> OutputLottoList = lottoService.LottoAll(inputLotto);//가공된 데이터만 담겨있는 리스트 가져오기
//
//        Long totalWinning = 0L;
//
//        for(OutputLotto outputLotto : OutputLottoList){//누적 금액 생성
//
//            Long winningCal = Long.valueOf((outputLotto.getWinning()));
//
//            log.info("회차"+outputLotto.getRound() + "당첨금 : "+winningCal);
//
//            totalWinning += winningCal;
//
//
//            log.info("회차"+outputLotto.getRound() + "누적 금액 :"+totalWinning);
//
//        }
//
//
//
//        log.info(OutputLottoList);
////        log.info("리버스 전후@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@리버스 전후@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
////        Collections.reverse(OutputLottoList);
////        log.info(OutputLottoList);
//
//        log.info("누적금액 : "+totalWinning);
//
//        model.addAttribute("OutputLottoList",OutputLottoList);
//
//        model.addAttribute("totalWinning",totalWinning);
//
//        return "lottoResult";
//
//    }

    @PostMapping("/lottoResult")
    public @ResponseBody Map<String, Object> getLotto(@RequestBody InputLotto inputLotto){

        log.info("들어간값 : "+inputLotto);
        log.info("1번 : "+inputLotto.getNum1());
        log.info("2번 : "+inputLotto.getNum2());
        log.info("3번 : "+inputLotto.getNum3());
        log.info("4번 : "+inputLotto.getNum4());
        log.info("5번 : "+inputLotto.getNum5());
        log.info("6번 : "+inputLotto.getNum6());


        List<OutputLotto> OutputLottoList = lottoService.LottoAll(inputLotto);//가공된 데이터만 담겨있는 리스트 가져오기

        Long totalWinning = 0L;

        for(OutputLotto outputLotto : OutputLottoList){//누적 금액 생성

            Long winningCal = Long.valueOf((outputLotto.getWinning()));

            log.info("회차"+outputLotto.getRound() + "당첨금 : "+winningCal);

            totalWinning += winningCal;


            log.info("회차"+outputLotto.getRound() + "누적 금액 :"+totalWinning);

        }



        log.info(OutputLottoList);
//        log.info("리버스 전후@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@리버스 전후@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//        Collections.reverse(OutputLottoList);
//        log.info(OutputLottoList);

        log.info("누적금액 : "+totalWinning);

        Map<String, Object> lottoMap = new HashMap<>();
        lottoMap.put("OutputLottoList", OutputLottoList);
        lottoMap.put("totalWinning", totalWinning);
        return lottoMap;

    }



}
