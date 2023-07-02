package com.appointments.mapper;

import com.appointments.database.entity.Appointment;
import com.appointments.dto.AppointmentResponseDto;

public class AppointmentMapper {

    public static AppointmentResponseDto toAppointmentResponseDto(Appointment appointment){
        AppointmentResponseDto dto = new AppointmentResponseDto();
        dto.setDate(appointment.getDate());
        dto.setTime(appointment.getTime());
        dto.setAddress(appointment.getDepartment().getAddress());
        dto.setFloor(appointment.getDepartment().getFloor());
        dto.setDepartmentName(appointment.getDepartment().getName());

        return dto;
    }
}
