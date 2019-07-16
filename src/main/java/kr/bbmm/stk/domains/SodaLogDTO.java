package kr.bbmm.stk.domains;

import lombok.Data;

import java.time.Instant;

public class SodaLogDTO {

    @Data
    public static class LIST {
        public Long id;
        public int pushCount;
        public Instant buildAt;
        public Instant createdAt;

    }

    @Data
    public static class FORM_DATA {
        public Long id;
        public int pushCount;
        public Instant buildAt;

        public SodaLog toSodaLog() {

            SodaLog sodaLog = new SodaLog();
            sodaLog.setId(id);
            sodaLog.setPushCount(pushCount);
            sodaLog.setBuildAt(buildAt);
            return sodaLog;
        }
    }
}
