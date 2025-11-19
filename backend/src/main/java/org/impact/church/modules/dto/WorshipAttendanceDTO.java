package org.impact.church.modules.dto;

import org.impact.church.modules.model.WorshipAttendance;

import java.time.LocalDate;

public record WorshipAttendanceDTO(Long id, LocalDate serviceDate, int menCount,
                                   int womenCount, int childrenCount) {

    public WorshipAttendanceDTO(WorshipAttendance worshipAttendance){
        this(worshipAttendance.getId(),
                worshipAttendance.getServiceDate(),
                worshipAttendance.getMenCount(),
                worshipAttendance.getWomenCount(),
                worshipAttendance.getChildrenCount());
    }
}
