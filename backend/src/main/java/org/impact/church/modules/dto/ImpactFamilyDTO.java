package org.impact.church.modules.dto;

import org.impact.church.modules.model.ImpactFamily;
import org.impact.church.modules.model.Member;

public record ImpactFamilyDTO (Long id, String name, MemberDTO supervisor){

    public ImpactFamilyDTO (ImpactFamily impactFamily){
        this(impactFamily.getId(),
                impactFamily.getName(),
                new MemberDTO(impactFamily.getSupervisor()));
    }
}
