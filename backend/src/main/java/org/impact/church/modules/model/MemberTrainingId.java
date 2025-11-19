package org.impact.church.modules.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class MemberTrainingId implements Serializable {
    private Long memberId;
    private Long trainingId;
}
