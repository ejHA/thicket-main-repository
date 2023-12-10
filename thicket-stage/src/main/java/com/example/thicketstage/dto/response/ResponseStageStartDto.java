package com.example.thicketstage.dto.response;

import com.example.thicketstage.domain.StageStart;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class ResponseStageStartDto {

    private LocalDate date;

    private List<LocalTime> times;

    public ResponseStageStartDto(StageStart stageStart) {
        this.date = stageStart.getDate();
        this.times = stageStart.getTimes();
    }
}