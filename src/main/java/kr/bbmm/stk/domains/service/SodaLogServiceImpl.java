package kr.bbmm.stk.domains.service;

import kr.bbmm.stk.domains.SodaLog;
import kr.bbmm.stk.domains.SodaLogDTO;
import kr.bbmm.stk.domains.repository.SodaLogRepository;
import kr.bbmm.stk.helper.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SodaLogServiceImpl implements SodaLogService {

    private SodaLogRepository sodaLogRepository;

    public SodaLogServiceImpl(SodaLogRepository sodaLogRepository) {
        this.sodaLogRepository = sodaLogRepository;
    }

    @Override
    public Page<SodaLogDTO.LIST> findAll(Pageable pageable) {
        Page<SodaLog> sodaLogPage = sodaLogRepository.findAll(pageable);
        return new PageImpl<>(Mapper.map(sodaLogPage.getContent()), pageable, sodaLogPage.getTotalElements());
    }

    @Override
    public SodaLog save(SodaLog toSodaLog) {
        return sodaLogRepository.save(toSodaLog);
    }

    @Override
    public void delete(Long id) {
        sodaLogRepository.deleteById(id);
    }
}
