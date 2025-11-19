package org.impact.church.modules.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "member_departments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MemberDepartment implements Serializable {

    @EmbeddedId
    private MemberDepartmentId id = new MemberDepartmentId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("memberId")
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("departmentId")
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
