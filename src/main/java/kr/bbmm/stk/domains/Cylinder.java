package kr.bbmm.stk.domains;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CYLINDER_TBL")
@Data
public class Cylinder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TOTAL_COUNT")
    private int totalCount;

    @Column(name = "PERIOD")
    private int period;

    @Column(name = "DRINK_PER_DAY")
    private int drinkPerDay;

    @Column(name = "PUSH_AVG")
    private int pushAvg;

    @Column(name = "EXHAUSTED")
    private boolean exhausted = false;

    @Column(name = "START_DATE")
    private Instant startDate;

    @Column(name = "END_DATE")
    private Instant endDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "CYLINDER_LOG_MAP_TBL",
            joinColumns = @JoinColumn(name = "CYLINDER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "LOG_ID", referencedColumnName = "ID")
    )
    private List<SodaLog> sodaLogList = new ArrayList<>();

}
