package kr.bbmm.stk.domains;

import lombok.Data;

import java.time.Instant;

public class CylinderDTO {

    @Data
    public static class ONE {
        private Long id;
        private int totalCount = 0;
        private int period = 1;
        private int drinkPerDay = 0;
        private int pushAvg = 0;
        private boolean exhausted = false;
        private Instant startDate;
        private Instant endDate;
    }

    @Data
    public static class LIST {
    }
}
