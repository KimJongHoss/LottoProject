package hg.jh.luko6.repository;

import hg.jh.luko6.entity.Lotto;
import hg.jh.luko6.entity.VisitStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitStatsRepository extends JpaRepository<VisitStats,Long> {
}
