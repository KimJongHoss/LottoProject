package hg.jh.luko6.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.extern.log4j.Log4j2;

@Entity
@Log4j2
public class Lotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer round;

    String lottoDate;

    String firstWinner;
    String firstWinning;
    String secondWinner;
    String secondWinning;
    String thirdWinner;
    String thirdWinning;
    String fourthWinner;
    String fourthWinning;
    String fifthWinner;
    String fifthWinning;
    String no1;
    String no2;
    String no3;
    String no4;
    String no5;
    String no6;
    String no7;

}
