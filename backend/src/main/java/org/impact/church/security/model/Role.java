package org.impact.church.security.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles", schema = "public")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String name; // ex: SUPER_ADMIN, CHURCH_ADMIN, DEPARTMENT_LEADER, TRAINING_LEADER

    @Column(length = 255)
    private String description;
}
