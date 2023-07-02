package com.appointments.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class AvailableTimeSlotsRequestDto {

    private LocalDate date;
    private List<String> time;
    private String departmentName;
}
