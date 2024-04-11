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

    // VisitStatsRepository를 주입받는 생성자

    public Long getVisitorCount(HttpServletRequest request, HttpServletResponse response) {


        Cookie[] cookies = request.getCookies();

        Optional<VisitStats> optional =visitStatsRepository.findById(2L);

            VisitStats visitStats1 = optional.get();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(VISIT_COUNTER_COOKIE)) {
                    // 쿠키가 이미 존재한다면 DB에서 방문자 수를 가져와 반환합니다.
                    return (visitStats1.getVisitorCount());
                }
            }
        }


        // DB의 방문자 수를 증가시키는 작업 추가
        visitStats1.setVisitorCount(visitStats1.getVisitorCount()+1);

        visitStatsRepository.save(visitStats1);

        Long visitorCount = visitStats1.getVisitorCount();


        // "visitCounter" 쿠키가 존재하지 않는 경우 새로운 쿠키 생성
        Cookie newCookie = new Cookie(VISIT_COUNTER_COOKIE, "");
        newCookie.setMaxAge(24 * 60 * 60); // 1일
        newCookie.setPath("/"); // 애플리케이션 전역에서 사용 가능하도록 지정
        response.addCookie(newCookie);


        return visitorCount;
        // 쿠키가 존재하지 않으면 방문자 수를 증가시키고 쿠키를 생성합니다.
    }

    public VisitStats getVisitStats(Long id){

        Optional<VisitStats> visitStats = visitStatsRepository.findById(id);

        if(visitStats.isPresent()){
            return visitStats.get();
        }else{
            return null;
        }



    }

    public void save(VisitStats visitStats){

        visitStatsRepository.save(visitStats);

    }



}