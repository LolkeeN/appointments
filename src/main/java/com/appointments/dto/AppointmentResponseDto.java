package com.appointments.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class AppointmentResponseDto {

    private String departmentName;
    private LocalDate date;
    private String time;
    private String address;
    private int floor;
}
