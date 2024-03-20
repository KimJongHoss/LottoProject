package hg.jh.luko6.repository;

import hg.jh.luko6.entity.InputLotto;
import hg.jh.luko6.entity.Lotto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LottoRepository extends JpaRepository<Lotto,Integer> {
}
