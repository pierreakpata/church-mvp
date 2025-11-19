package org.impact.church.modules.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "worship_attendance")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class WorshipAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_date", nullable = false)
    private LocalDate serviceDate;

    @Column(name = "men_count")
    private int menCount = 0;

    @Column(name = "women_count")
    private int womenCount = 0;

    @Column(name = "children_count")
    private int childrenCount = 0;

    @Transient
    public int getTotal() {
        return menCount + womenCount + childrenCount;
    }

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
