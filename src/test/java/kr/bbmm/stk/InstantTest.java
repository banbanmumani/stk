package kr.bbmm.stk;

import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class InstantTest {

    @Test
    public void instantPeriodTest() {
        String beginStr = "2019-01-01T00:30:00z";
        String endStr = "2019-01-01T03:00:00z";

        Instant begin = Instant.parse(beginStr);
        Instant end = Instant.parse(endStr);

        double period = begin.until(end, ChronoUnit.HOURS) / 24d;

        System.out.println(period);
        System.out.println(Math.ceil(period));
    }
}
