package kr.bbmm.stk.domains.service;

import kr.bbmm.stk.domains.Cylinder;
import kr.bbmm.stk.domains.CylinderDTO;
import kr.bbmm.stk.domains.SodaLog;
import kr.bbmm.stk.domains.SodaLogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SodaLogService {
    Page<SodaLogDTO.LIST> findAll(Pageable pageable);

    SodaLog save(SodaLog toSodaLog);

    void delete(Long id);

    CylinderDTO.ONE findActivateCylinderInfo();

    void exhaustCylinder(Long id);

    Cylinder findCylinderInfoById(Long id);

    List<CylinderDTO.ONE> findCylinderList();
}
