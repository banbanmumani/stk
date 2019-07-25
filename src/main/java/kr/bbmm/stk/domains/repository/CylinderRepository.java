package kr.bbmm.stk.domains.repository;

import kr.bbmm.stk.domains.Cylinder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CylinderRepository extends JpaRepository<Cylinder, Long>, JpaSpecificationExecutor<Cylinder> {

    Optional<Cylinder> findFirstByExhaustedFalseOrderByEndDateDesc();

}
