package com.appointments.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class CreateAppointmentDto {

    private String userId;
    private String departmentName;
    private LocalDate date;
    private String time;
}
