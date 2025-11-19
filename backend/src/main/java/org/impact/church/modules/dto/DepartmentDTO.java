package org.impact.church.modules.dto;

import org.impact.church.modules.model.Department;
import org.impact.church.modules.model.Member;

public record DepartmentDTO (Long id, String name, String description, MemberDTO leader){

    public DepartmentDTO (Department department){
        this(department.getId(),
                department.getName(),
                department.getDescription(),
                new MemberDTO(department.getLeader()));
    }
}
