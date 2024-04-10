package hg.jh.luko6.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Entity
@Log4j2
@Data
public class VisitStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long visitorCount;
    private Long userCount;

//    이용자 수를 증가시키는 메서드
    public void addUserCount(){
        if (userCount != null){
            userCount++;
        }else{
            userCount = 1L;
        }
    }

//    방문자 수를 증가시키는 메서드
    public void addVisitorCount(){
        if (visitorCount != null){
            visitorCount++;
        }else{
            visitorCount = 1L;
        }
    }

}
