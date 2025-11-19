package org.impact.church.tenant.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "churches", schema = "public")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(name = "schema_name", unique = true, nullable = false)
    private String schemaName;

    private String city;
    private String country;

    @Column(name = "created_at")
    private OffsetDateTime createdAt = OffsetDateTime.now();
}
