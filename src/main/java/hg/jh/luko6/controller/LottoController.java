package hg.jh.luko6.controller;

import hg.jh.luko6.DTO.NumberDTO;
import hg.jh.luko6.entity.*;
import hg.jh.luko6.repository.VisitStatsRepository;
import hg.jh.luko6.service.LottoService;
import hg.jh.luko6.service.NumberCheckService;
import hg.jh.luko6.service.ProductService;
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
    private final ProductService productService;
    private final VisitStatsRepository visitStatsRepository;

    @GetMapping("/")//홈페이지 시작시 index로 가는 메서드
//    @ResponseBody//협업용
//    public VisitStats index(HttpServletRequest request//협업용
//            , HttpServletResponse response){//협업용
    public String index(){

        return "index";
//        return optionalVisitStats.orElse(null);
    }

    @GetMapping("/stats")//방문자/이용자 수 view에 보내기
    public @ResponseBody Map<String, Object> getStats(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> stats = new HashMap<>();

        visitStatsService.getVisitorCount(request, response);//방문자수 불러오기

//        return optionalVisitStats.orElse(null);
        stats.put("userCount",  visitStatsService.getStatsById(1L).getUserCount());//이용자수 맵에 넣기
        stats.put("visitorCount", visitStatsService.getStatsById(1L).getVisitorCount());//방문자수 맵에 넣기
        return stats;
    }

    @PostMapping("/lottoResult")//로또 기능
    public @ResponseBody ResponseEntity<?> getLotto(@RequestBody InputLotto inputLotto){

        float logCabinPrice = 4500000f;
        float cochoCakePrice = 5900f;
        float circusTentPrice = 748000f;
        float gameControllerPrice = 45050f;
        float lockedSafedPrice = 1144000f;
        float submarinePrice = 1400000000000f;

        NumberDTO dto = numberCheckService.getInputEntity(inputLotto);//가져온 번호 6개를 이용해서 dto 생성
        // NumberCheckService를 사용하여 유효성 검사 수행
        boolean isValid = numberCheckService.processNumberDTO(dto);
        if (!isValid) {
            // 유효하지 않은 경우 400 Bad Request를 반환
            return ResponseEntity.badRequest().body("유효하지 않은 입력입니다.");
        }

        List<OutputLotto> OutputLottoList = lottoService.LottoAll(inputLotto);//가공된 데이터만 담겨있는 리스트 가져오기

        Long totalWinning = lottoService.makeTotalWinning(OutputLottoList);

        lottoService.addPercentage(totalWinning);
        lottoService.calculatePercentage(totalWinning);

        int Ranking = ((int)(lottoService.calculatePercentage(totalWinning)*100));

        Map<String, Object> lottoMap = new HashMap<>();

        lottoMap.put("Ranking",Ranking);

        lottoMap.put("OutputLottoList", OutputLottoList);
        lottoMap.put("totalWinning", totalWinning);

//        각 항목을 소수점 세자리까지만 맵에 담기

        float logCabin = productService.onlyThreeDecimalPlaces(totalWinning, logCabinPrice);
        float cochoCake = productService.onlyThreeDecimalPlaces(totalWinning, cochoCakePrice);
        float circusTent = productService.onlyThreeDecimalPlaces(totalWinning, circusTentPrice);
        float gameController = productService.onlyThreeDecimalPlaces(totalWinning, gameControllerPrice);
        float lockedSafed = productService.onlyThreeDecimalPlaces(totalWinning, lockedSafedPrice);
        float submarine = productService.onlyThreeDecimalPlaces(totalWinning, submarinePrice);

//        각 항목을 맵에 넣기
        lottoMap.put("logCabin", logCabin);
        lottoMap.put("cochoCake", cochoCake);
        lottoMap.put("circusTent", circusTent);
        lottoMap.put("gameController", gameController);
        lottoMap.put("lockedSafed", lockedSafed);
        lottoMap.put("submarine", submarine);

//        VisitStats 테이블에서 id가 1인 레코드 조회
        Optional<VisitStats> optionalVisitStats = visitStatsRepository.findById(1L);
//       로직이 돌아가면 이용자수에 +1하기
        if (optionalVisitStats.isPresent()) {
            VisitStats visitStats = visitStatsService.getStatsById(1L);

            visitStats.setUserCount(visitStats.getUserCount()+1L);//사용자수 1증가시키는 메서드 호출

            visitStatsService.saveStatsValue(visitStats);

            lottoMap.put("useUserCount", visitStats.getUserCount());
            lottoMap.put("visitorCount", visitStats.getVisitorCount());
        }

        return ResponseEntity.ok(lottoMap);

    }

}
