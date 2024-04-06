package hg.jh.luko6.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Percentage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    Long totalWinning;



}
