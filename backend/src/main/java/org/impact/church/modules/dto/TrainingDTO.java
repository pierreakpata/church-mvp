package org.impact.church.modules.dto;

import org.impact.church.modules.model.Training;

import java.time.LocalDate;

public record TrainingDTO(Long id, String titre, String description, LocalDate startDate, LocalDate endDate) {

    public TrainingDTO(Training training){
        this(training.getId(),
                training.getTitle(),
                training.getDescription(),
                training.getStartDate(),
                training.getEndDate());
    }
}
