package hg.jh.luko6.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Entity
@Log4j2
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class OutputLotto {

    @Id
    Integer round;
    String winning;
    String place;

}
