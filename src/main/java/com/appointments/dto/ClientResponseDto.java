package com.appointments.dto;

import java.util.List;
import lombok.Data;

@Data
public class ClientResponseDto {

    private String firstName;
    private String lastName;
    private String email;
    private String avatarColor;
    private List<AppointmentResponseDto> upcoming;
    private List<AppointmentResponseDto> previous;

}
