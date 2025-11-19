package org.impact.church.modules.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "impact_families")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ImpactFamily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @OneToOne
    @JoinColumn(name = "supervisor_id")
    private Member supervisor;

    @OneToMany(mappedBy = "impactFamily", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Member> members = new HashSet<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
