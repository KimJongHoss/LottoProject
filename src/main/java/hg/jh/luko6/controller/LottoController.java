package hg.jh.luko6.controller;


import hg.jh.luko6.entity.InputLotto;
import hg.jh.luko6.entity.Lotto;
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

        List<Lotto> lottoList = lottoService.LottoAll(inputLotto);







        return "lottoResult";

    }



}
