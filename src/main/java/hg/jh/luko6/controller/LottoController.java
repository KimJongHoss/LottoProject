package hg.jh.luko6.controller;

import hg.jh.luko6.DTO.NumberDTO;
import hg.jh.luko6.entity.*;
import hg.jh.luko6.repository.VisitStatsRepository;
import hg.jh.luko6.service.LottoService;
import hg.jh.luko6.service.NumberCheckService;
import hg.jh.luko6.service.VisitStatsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.*;

@Controller
@Log4j2
@RequiredArgsConstructor
public class LottoController {

    private final LottoService lottoService;
    private final VisitStatsService visitStatsService;
    private final NumberCheckService numberCheckService;
    private final VisitStatsRepository visitStatsRepository;

    @GetMapping("/")//홈페이지 시작시 index로 가는 메서드
//    @ResponseBody//협업용
//    public VisitStats index(HttpServletRequest request//협업용
//            , HttpServletResponse response){//협업용
    public String index(){

        log.info("index로 갑니당");

        return "index";
//        return optionalVisitStats.orElse(null);
    }

    @GetMapping("/stats")//방문자/이용자 수 view에 보내기
    public @ResponseBody Map<String, Object> getStats(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> stats = new HashMap<>();

        Long visitorCount = visitStatsService.getVisitorCount(request, response);//방문자수 불러오기

        stats.put("visitorCount", visitorCount);//방문자수 맵에 넣기
        // 추가 데이터 처리

        Optional<VisitStats> optionalVisitStats = visitStatsRepository.findById(1L);//visitStats에 있는 id가 1인 컬럼을 불러온다
        VisitStats visitStats = optionalVisitStats.get();//optional은 값이 존재한다면 반환, 없을 경우 NoSuchElementException 발생
        Long userCount = visitStats.getUserCount();

//        return optionalVisitStats.orElse(null);
        stats.put("userCount", userCount);//이용자수 맵에 넣기
        return stats;
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

    @PostMapping("/lottoResult")//로또 기능
    public @ResponseBody ResponseEntity<?> getLotto(@RequestBody InputLotto inputLotto){

        NumberDTO dto = numberCheckService.getInputEntity(inputLotto);//가져온 번호 6개를 이용해서 dto 생성
        // NumberCheckService를 사용하여 유효성 검사 수행
        boolean isValid = numberCheckService.processNumberDTO(dto);
        if (!isValid) {
            // 유효하지 않은 경우 400 Bad Request를 반환
            return ResponseEntity.badRequest().body("유효하지 않은 입력입니다.");
        }

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


        log.info("누적금액 : "+totalWinning);

        float logCabinPrice = 4500000f;
        float cochoCakePrice = 5900f;
        float circusTentPrice = 748000f;
        float gameControllerPrice = 45050f;
        float lockedSafedPrice = 1144000f;
        float submarinePrice = 1400000000000f;

        // VisitStats 테이블에서 id가 1인 레코드 조회
        Optional<VisitStats> optionalVisitStats = visitStatsRepository.findById(1L);

        lottoService.addPercentage(totalWinning);
        lottoService.calculatePercentage(totalWinning);

        log.info("상위"+((int)(lottoService.calculatePercentage(totalWinning)*100))+"%");

        int Ranking = ((int)(lottoService.calculatePercentage(totalWinning)*100));

        Map<String, Object> lottoMap = new HashMap<>();

        lottoMap.put("Ranking",Ranking);

        lottoMap.put("OutputLottoList", OutputLottoList);
        lottoMap.put("totalWinning", totalWinning);

//        각 항목을 소수점 세자리까지만 맵에 담기
        DecimalFormat df = new DecimalFormat("#.###");
        String logCabinValue = df.format(totalWinning / logCabinPrice);
        float logCabin = Float.parseFloat(logCabinValue);

        String cochoCakeValue = df.format(totalWinning / cochoCakePrice);
        float cochoCake = Float.parseFloat(cochoCakeValue);

        String circusTentValue = df.format(totalWinning / circusTentPrice);
        float circusTent = Float.parseFloat(circusTentValue);

        String gameControllerValue = df.format(totalWinning / gameControllerPrice);
        float gameController = Float.parseFloat(gameControllerValue);

        String lockedSafedValue = df.format(totalWinning / lockedSafedPrice);
        float lockedSafed = Float.parseFloat(lockedSafedValue);

        String submarineValue = df.format(totalWinning / submarinePrice);
        float submarine = Float.parseFloat(submarineValue);

//        각 항목을 맵에 넣기
        lottoMap.put("logCabin", logCabin);
        lottoMap.put("cochoCake", cochoCake);
        lottoMap.put("circusTent", circusTent);
        lottoMap.put("gameController", gameController);
        lottoMap.put("lockedSafed", lockedSafed);
        lottoMap.put("submarine", submarine);

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

        return ResponseEntity.ok(lottoMap);

    }

}
