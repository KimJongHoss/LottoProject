package hg.jh.luko6.repository;

import hg.jh.luko6.entity.Percentage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PercentageRepository extends JpaRepository<Percentage,Long > {

    @Query(value = "SELECT count(*) FROM percentage WHERE total_winning > :totalWinning", nativeQuery = true)
    Long findByTotalWinning(@Param("totalWinning") Long totalWinning);



}
