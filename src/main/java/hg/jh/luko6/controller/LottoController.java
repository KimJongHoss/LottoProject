package hg.jh.luko6.controller;


import hg.jh.luko6.entity.*;
import hg.jh.luko6.repository.VisitStatsRepository;
import hg.jh.luko6.service.LottoService;
import hg.jh.luko6.service.VisitStatsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@Log4j2
@RequiredArgsConstructor
public class LottoController {

    private final LottoService lottoService;
    private  final VisitStatsService visitStatsService;
    @Autowired
    private VisitStatsRepository visitStatsRepository;




    @GetMapping("/")
//    @ResponseBody//협업용
//    public VisitStats index(HttpServletRequest request//협업용
//            , HttpServletResponse response){//협업용
    public String index(HttpServletRequest request
            , HttpServletResponse response){


//        방문자수
        Long visitorCount = visitStatsService.getVisitorCount(request, response);


        log.info("방문자수: "+visitorCount+"명");


//        이용자수
        Optional<VisitStats> optionalVisitStats = visitStatsRepository.findById(1L);//visitStats에 있는 id가 1인 컬럼을 불러온다
        VisitStats visitStats = optionalVisitStats.get();//optional은 값이 존재한다면 반환, 없을 경우 NoSuchElementException 발생
        Long userCount = visitStats.getUserCount();
        log.info("이용자수: "+userCount+"명");

        log.info("index로 갑니당");
//        return optionalVisitStats.orElse(null);
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

        // VisitStats 테이블에서 id가 1인 레코드 조회
        Optional<VisitStats> optionalVisitStats = visitStatsRepository.findById(1L);




        lottoService.addPercentage(totalWinning);
        lottoService.calculatePercentage(totalWinning);
        log.info("////////////////////////////////");
        log.info("상위"+((int)(lottoService.calculatePercentage(totalWinning)*100))+"%");
        log.info("////////////////////////////////");

        int Ranking = ((int)(lottoService.calculatePercentage(totalWinning)*100));

        Map<String, Object> lottoMap = new HashMap<>();

        lottoMap.put("Ranking",Ranking);








        lottoMap.put("OutputLottoList", OutputLottoList);
        lottoMap.put("totalWinning", totalWinning);
//       로직이 돌아가면 이용자수에 +1하기

        if (optionalVisitStats.isPresent()) {
            VisitStats visitStats = optionalVisitStats.get();
            if (OutputLottoList != null) {


                visitStats.addUserCount();//사용자수 1증가시키는 메서드 호출
                visitStatsRepository.save(visitStats);
                log.info(visitStats);


            }
            lottoMap.put("usercount", visitStats.getUserCount());
        }
        log.info("로또 맵:"+lottoMap);


        return lottoMap;

    }



}
