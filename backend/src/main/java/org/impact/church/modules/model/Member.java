package org.impact.church.modules.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "members")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(length = 10)
    private String gender;

    private LocalDate birthDate;

    @Column(length = 150)
    private String email;

    @Column(length = 30)
    private String phoneNumber;

    @Column(length = 255)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "impact_family_id")
    private ImpactFamily impactFamily;

    @Column(name = "joined_at")
    private LocalDate joinedAt = LocalDate.now();

    @Column(length = 50)
    private String status = "ACTIVE";

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MemberDepartment> memberDepartments = new HashSet<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MemberTraining> memberTrainings = new HashSet<>();
}
