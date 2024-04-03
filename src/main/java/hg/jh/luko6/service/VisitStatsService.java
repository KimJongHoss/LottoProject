package hg.jh.luko6.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
@Log4j2
public class VisitStatsService {

    private static final String VISIT_COUNTER_COOKIE = "visitCounter";

    public Long getVisitorCount(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Long visitorCount = 0L;

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

    public void incrementVisitorCount(HttpServletRequest request, HttpServletResponse response) {
        Long visitorCount = getVisitorCount(request) + 1;
        Cookie cookie = new Cookie(VISIT_COUNTER_COOKIE, String.valueOf(visitorCount));
        cookie.setMaxAge(24 * 60 * 60); // 1일
        cookie.setPath("/");
        response.addCookie(cookie);

        // 방문자 수 증가 시 로그 출력
        log.info("방문자수 1명 추가: " + visitorCount);
    }
}