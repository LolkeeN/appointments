package com.appointments.service;

import com.appointments.database.entity.Appointment;
import com.appointments.database.entity.enums.AppointmentType;
import com.appointments.database.repository.AppointmentRepository;
import com.appointments.database.repository.ClientRepository;
import com.appointments.database.repository.DepartmentRepository;
import com.appointments.dto.AppointmentResponseDto;
import com.appointments.dto.CreateAppointmentDto;
import com.appointments.exception.ClientNotFoundException;
import com.appointments.exception.DepartmentNotFoundException;
import com.appointments.mapper.AppointmentMapper;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DepartmentRepository departmentRepository;
    private final ClientRepository clientRepository;

    public void createAppointment(CreateAppointmentDto dto) {
        Appointment appointment = new Appointment();
        appointment.setTime(dto.getTime());
        appointment.setDate(dto.getDate());
        appointment.setClient(clientRepository.findById(dto.getUserId()).orElseThrow(
                () -> new ClientNotFoundException("Client " + dto.getUserId() + " not found")
        ));
        appointment.setDepartment(departmentRepository.getByName(dto.getDepartmentName()).orElseThrow(
                () -> new DepartmentNotFoundException("Department " + dto.getDepartmentName() + " not found")
        ));

        appointmentRepository.save(appointment);
    }

    public List<AppointmentResponseDto> getAllAppointmentsByClientId(String clientId) {
        List<Appointment> allByClientId = appointmentRepository.getAllByClientId(clientId);
        return allByClientId.stream()
                .map(AppointmentMapper::toAppointmentResponseDto)
                .collect(Collectors.toList());
    }


    @Scheduled(cron = "0 0 * ? * *")
    public void changeAppointmentStatusToPreviousIfTimeHasPassed() {
        List<Appointment> all = appointmentRepository.findAllByAppointmentType(AppointmentType.UPCOMING);
        for (Appointment appointment : all) {
            String[] split = appointment.getTime().split(":");
            if (appointment.getDate().atTime(Integer.parseInt(split[0]) + 1, Integer.parseInt(split[1]))
                    .isBefore(Instant.ofEpochMilli(System.currentTimeMillis())
                            .atZone(ZoneId.systemDefault()).toLocalDateTime())) {
                appointment.setAppointmentType(AppointmentType.PREVIOUS);
                appointmentRepository.save(appointment);
            }
        }
    }
}
