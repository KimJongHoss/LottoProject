package hg.jh.luko6.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InputLotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int num1;
    int num2;
    int num3;
    int num4;
    int num5;
    int num6;
}
