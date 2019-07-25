package kr.bbmm.stk.domains.service;

import kr.bbmm.stk.domains.Cylinder;
import kr.bbmm.stk.domains.CylinderDTO;
import kr.bbmm.stk.domains.SodaLog;
import kr.bbmm.stk.domains.SodaLogDTO;
import kr.bbmm.stk.domains.repository.CylinderRepository;
import kr.bbmm.stk.domains.repository.SodaLogRepository;
import kr.bbmm.stk.helper.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class SodaLogServiceImpl implements SodaLogService {

    private SodaLogRepository sodaLogRepository;
    private CylinderRepository cylinderRepository;

    public SodaLogServiceImpl(SodaLogRepository sodaLogRepository, CylinderRepository cylinderRepository) {
        this.sodaLogRepository = sodaLogRepository;
        this.cylinderRepository = cylinderRepository;
    }

    @Override
    public Page<SodaLogDTO.LIST> findAll(Pageable pageable) {
        Page<SodaLog> sodaLogPage = sodaLogRepository.findAll(pageable);
        return new PageImpl<>(Mapper.map(sodaLogPage.getContent()), pageable, sodaLogPage.getTotalElements());
    }

    @Transactional
    @Override
    public SodaLog save(SodaLog toSodaLog) {
        Cylinder cylinder = findActivateCylinder();

        int cylinderTotalPushCount = sodaLogRepository.getTotalPushCount(cylinder.getId()) + toSodaLog.getPushCount();
        int period = (int) Math.ceil((double) cylinder.getStartDate().until(toSodaLog.getBuildAt(), ChronoUnit.HOURS) / 24d);
        int totalBuildCount = sodaLogRepository.getTotalBuildCount(cylinder.getId()) + 1;
        int drinkPerDay = totalBuildCount / period;
        int pushAvg = cylinderTotalPushCount / totalBuildCount;

        cylinder.setTotalCount(cylinderTotalPushCount);
        cylinder.setPeriod(period);
        cylinder.setDrinkPerDay(drinkPerDay);
        cylinder.setPushAvg(pushAvg);


        toSodaLog.setCylinder(cylinder);

        cylinderRepository.save(cylinder);

        return sodaLogRepository.save(toSodaLog);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        sodaLogRepository.deleteById(id);
    }

    @Override
    public CylinderDTO.ONE findActivateCylinderInfo() {
        Cylinder cylinder = findActivateCylinder();

        return Mapper.map(cylinder);
    }

    @Transactional
    private Cylinder findActivateCylinder() {
        Optional<Cylinder> cylinderOptional = cylinderRepository.findFirstByExhaustedFalseOrderByEndDateDesc();
        Cylinder cylinder = null;

        if (cylinderOptional.isPresent()) {
            cylinder = cylinderOptional.get();
        } else {
            cylinder = new Cylinder();
            cylinderRepository.save(cylinder);
        }
        return cylinder;
    }
}
