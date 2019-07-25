package kr.bbmm.stk.domains.repository;

import kr.bbmm.stk.domains.SodaLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SodaLogRepository extends JpaRepository<SodaLog, Long> {

    @Query(value = "SELECT SUM(s.pushCount) FROM SodaLog s WHERE s.cylinder.id = :id")
    int getTotalPushCount(@Param(value = "id") Long id);

    @Query(value = "SELECT COUNT(s) FROM SodaLog s WHERE s.cylinder.id = :id")
    int getTotalBuildCount(@Param(value = "id") Long id);
}
