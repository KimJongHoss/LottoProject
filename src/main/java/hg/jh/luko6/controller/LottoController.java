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

import java.util.List;

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

    @PostMapping("/getLotto")
    public String getLotto(InputLotto inputLotto, Model model){

        List<OutputLotto> OutputLottoList = lottoService.LottoAll(inputLotto);//가공된 데이터만 담겨있는 리스트 가져오기

        int totalWinning = 0;

        for(OutputLotto outputLotto : OutputLottoList){//누적 금액 생성

            int winningCal = Integer.parseInt(outputLotto.getWinning());

            log.info("회차"+outputLotto.getRound() + "당첨금 : "+winningCal);

            totalWinning += winningCal;


            log.info("회차"+outputLotto.getRound() + "누적 금액 :"+totalWinning);

        }

        log.info("누적금액 : "+totalWinning);

        model.addAttribute("OutputLottoList",OutputLottoList);

        model.addAttribute("totalWinning",totalWinning);

        return "lottoResult";

    }



}
