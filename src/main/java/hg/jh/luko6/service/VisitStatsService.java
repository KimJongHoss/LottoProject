package hg.jh.luko6.service;

import hg.jh.luko6.entity.VisitStats;
import hg.jh.luko6.repository.VisitStatsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class VisitStatsService {

    private static final String VISIT_COUNTER_COOKIE = "visitCounter";
    private final VisitStatsRepository visitStatsRepository;

    public Long getVisitorCount(HttpServletRequest request, HttpServletResponse response) {
        VisitStats visitStats = getStatsById(1L);

        log.info(visitStats+"getVisitorCount 들어오자마자");
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(VISIT_COUNTER_COOKIE)) {
                    // 쿠키가 이미 존재한다면 DB에서 방문자 수를 가져와 반환합니다.
                    return visitStats.getVisitorCount();
                }
            }
        }

        return giveMeCookie(response, visitStats);
//        return incrementVisitorCount(response);
    }

    public Long giveMeCookie(HttpServletResponse response, VisitStats visitStats){
        Cookie newCookie = new Cookie(VISIT_COUNTER_COOKIE, "");
        newCookie.setMaxAge(24 * 60 * 60); // 1일
        newCookie.setPath("/"); // 애플리케이션 전역에서 사용 가능하도록 지정
        response.addCookie(newCookie);

        // 쿠키가 존재하지 않으면 방문자 수를 증가시키고 쿠키를 생성합니다.
        log.info(visitStats+"invreament 가기 전 visitStats");
        log.info(response+"리스폰스");
        visitStats.setVisitorCount(visitStats.getVisitorCount()+1L);
        visitStatsRepository.save(visitStats);
        return visitStats.getVisitorCount();
    }

//    id로 방문자수/이용자수 값 가져오기
    public VisitStats getStatsById(Long id){
        Optional<VisitStats> optionalVisitStats = visitStatsRepository.findById(id);
        VisitStats visitStats = optionalVisitStats.get();
        return visitStats;
    }

    public void saveStatsValue (VisitStats visitStats){
        visitStatsRepository.save(visitStats);
    }

}