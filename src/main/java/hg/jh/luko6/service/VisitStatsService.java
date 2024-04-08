package hg.jh.luko6.service;

import hg.jh.luko6.entity.VisitStats;
import hg.jh.luko6.repository.VisitStatsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

@Service
@Log4j2
public class VisitStatsService {

    private static final String VISIT_COUNTER_COOKIE = "visitCounter";
    private static final String NO_COOKIE = "imnotCookie";
    private final VisitStatsRepository visitStatsRepository;
    private VisitStats visitStats;

    // VisitStatsRepository를 주입받는 생성자
    @Autowired
    public VisitStatsService(VisitStatsRepository visitStatsRepository) {
        this.visitStatsRepository = visitStatsRepository;
        initializeVisitStats(); // VisitStats 객체 초기화 메서드 호출
    }

    // VisitStats 객체 초기화 메서드
    private void initializeVisitStats() {

        Optional<VisitStats> optionalVisitStats = visitStatsRepository.findById(1L);
        visitStats = optionalVisitStats.orElseGet(VisitStats::new);
    }

    public Long getVisitorCount(HttpServletRequest request, HttpServletResponse response) {
        Long visitorCount = 0L;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(VISIT_COUNTER_COOKIE)) {
                    // 쿠키가 이미 존재한다면 DB에서 방문자 수를 가져와 반환합니다.
                    log.info("쿠키가 있네요!?!?!?");
                    log.info("for문 돌린 쿠키: "+cookie.getName());
                    return visitStats.getVisitorCount();
                }
            }
        }

        // 쿠키가 존재하지 않으면 방문자 수를 증가시키고 쿠키를 생성합니다.
        return incrementVisitorCount(request, response);
    }

    public Long incrementVisitorCount(HttpServletRequest request, HttpServletResponse response) {

        // DB의 방문자 수를 증가시키는 작업 추가
        visitStats.addVisitorCount();
        visitStatsRepository.save(visitStats);

        Long visitorCount = visitStats.getVisitorCount();
        Cookie[] cookies = request.getCookies();

        // "visitCounter" 쿠키가 존재하지 않는 경우 새로운 쿠키 생성
        Cookie newCookie = new Cookie(VISIT_COUNTER_COOKIE, "");
        newCookie.setMaxAge(24 * 60 * 60); // 1일
        newCookie.setPath("/"); // 애플리케이션 전역에서 사용 가능하도록 지정
        response.addCookie(newCookie);

        // 로그 추가: incrementVisitorCount 메서드가 호출되었음을 표시
        log.info("incrementVisitorCount 메서드 호출됨");

        return visitorCount;
    }


}