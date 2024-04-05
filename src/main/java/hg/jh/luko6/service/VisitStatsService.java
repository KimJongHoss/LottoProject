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
    @Autowired
    private VisitStatsRepository visitStatsRepository;
    private VisitStats visitStats;

    public Long getVisitorCount(HttpServletRequest request) {
        Long visitorCount = 0L;
        Cookie[] cookies = request.getCookies();


        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(VISIT_COUNTER_COOKIE)) {
                    visitorCount = Long.parseLong(cookie.getValue());
                    break;

                }

            }

        }


        return visitorCount;
    }

    public Long incrementVisitorCount(HttpServletRequest request, HttpServletResponse response) {
        Long visitorCount = 0L;
        Cookie[] cookies = request.getCookies();


        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(VISIT_COUNTER_COOKIE)) {
                    visitorCount = Long.parseLong(cookie.getValue()) + 1; // 방문자 수 증가
                    cookie.setValue(String.valueOf(visitorCount));
                    response.addCookie(cookie); // 쿠키 갱신
                    log.info("방문자수 1명 추가: " + visitorCount);
                    log.info("쿠키"+cookie);
                    return visitorCount;
                }
            }
        }

        // "visitCounter" 쿠키가 없는 경우 새로운 쿠키 생성
        Cookie newCookie = new Cookie(VISIT_COUNTER_COOKIE, "1");
        newCookie.setMaxAge(24 * 60 * 60); // 1일
        newCookie.setPath("/");
        response.addCookie(newCookie);
        visitStats.addVisitorCount();
        log.info("방문자수 1명 추가: 1");
        return 1L;
    }
}