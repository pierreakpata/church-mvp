package org.impact.church.modules.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member_trainings")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MemberTraining {

    @EmbeddedId
    private MemberTrainingId id = new MemberTrainingId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("memberId")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("trainingId")
    private Training training;

    private boolean completed = false;
}
