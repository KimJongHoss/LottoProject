package hg.jh.luko6.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Entity
@Log4j2
@Data
public class OutputLotto {

    @Id
    Integer round;
    String winning;
    String place;

}
