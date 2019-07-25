package kr.bbmm.stk.domains;

import lombok.Data;

import javax.persistence.*;
import java.time.Clock;
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
    private int totalCount = 0;

    @Column(name = "PERIOD")
    private int period = 1;

    @Column(name = "DRINK_PER_DAY")
    private int drinkPerDay = 0;

    @Column(name = "PUSH_AVG")
    private int pushAvg = 0;

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

    @PrePersist
    public void prePersist() {
        this.startDate = Instant.now(Clock.systemDefaultZone());
        this.endDate = this.startDate;
    }

    @PreUpdate
    public void preUpdate() {
        this.endDate = Instant.now(Clock.systemDefaultZone());
    }
}
