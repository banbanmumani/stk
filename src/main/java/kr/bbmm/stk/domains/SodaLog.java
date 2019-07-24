package kr.bbmm.stk.domains;

import lombok.Data;

import javax.persistence.*;
import java.time.Clock;
import java.time.Instant;

@Table(name = "SODA_LOG_TBL")
@Entity
@Data
public class SodaLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PUSH_COUNT")
    private int pushCount;

    @Column(name = "BUILD_AT")
    private Instant buildAt;

    @Column(name = "CREATED_AT")
    private Instant createdAt;

    @ManyToOne
    @JoinTable(name = "CYLINDER_LOG_MAP_TBL",
            joinColumns = @JoinColumn(name = "LOG_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "CYLINDER_ID", referencedColumnName = "ID")
    )
    private Cylinder cylinder;

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now(Clock.systemDefaultZone());
    }
}
