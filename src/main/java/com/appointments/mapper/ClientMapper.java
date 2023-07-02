package com.appointments.mapper;

import com.appointments.database.entity.Client;
import com.appointments.database.entity.enums.AppointmentType;
import com.appointments.dto.ClientResponseDto;
import java.util.stream.Collectors;

public class ClientMapper {

    public static ClientResponseDto toClientResponseDto(Client client) {
        ClientResponseDto dto = new ClientResponseDto();
        dto.setEmail(client.getEmail());
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setAvatarColor(client.getAvatarColor());
        dto.setEmail(client.getEmail());
        dto.setPrevious(client.getAppointments().stream()
                .filter(
                        x -> x.getAppointmentType() == AppointmentType.PREVIOUS
                )
                .map(AppointmentMapper::toAppointmentResponseDto)
                .collect(Collectors.toList()));
        dto.setUpcoming(client.getAppointments().stream()
                .filter(
                        x -> x.getAppointmentType() == AppointmentType.UPCOMING
                )
                .map(AppointmentMapper::toAppointmentResponseDto)
                .collect(Collectors.toList()));

        return dto;
    }
}
