package kr.bbmm.stk.helper;

import kr.bbmm.stk.domains.SodaLog;
import kr.bbmm.stk.domains.SodaLogDTO;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    public static List<SodaLogDTO.LIST> map(List<SodaLog> content) {
        return content.stream().map(l -> {
            SodaLogDTO.LIST dto = new SodaLogDTO.LIST();
            dto.setId(l.getId());
            dto.setPushCount(l.getPushCount());
            dto.setBuildAt(l.getBuildAt());
            dto.setCreatedAt(l.getCreatedAt());
            return dto;
        })
        .collect(Collectors.toList());
    }
}
