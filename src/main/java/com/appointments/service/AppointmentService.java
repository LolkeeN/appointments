package com.appointments.service;

import com.appointments.database.entity.Appointment;
import com.appointments.database.entity.Client;
import com.appointments.database.entity.Department;
import com.appointments.database.entity.enums.AppointmentType;
import com.appointments.database.repository.AppointmentRepository;
import com.appointments.database.repository.ClientRepository;
import com.appointments.database.repository.DepartmentRepository;
import com.appointments.dto.AppointmentResponseDto;
import com.appointments.dto.AvailableTimeSlotsRequestDto;
import com.appointments.dto.CreateAppointmentDto;
import com.appointments.exception.ClientNotFoundException;
import com.appointments.exception.DepartmentNotFoundException;
import com.appointments.exception.NotLoggedInException;
import com.appointments.mapper.AppointmentMapper;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private final EmailService emailService;
    private final ClientService clientService;
    public void createAppointment(CreateAppointmentDto dto) {
        Client client = clientRepository.findById(dto.getUserId()).orElseThrow(
                () -> new ClientNotFoundException("Client " + dto.getUserId() + " not found")
        );
        if (!clientService.isLoggedIn(client.getEmail())) {
            throw new NotLoggedInException("You need to login before making this request");
        }
        Appointment appointment = new Appointment();
        appointment.setTime(dto.getTime());
        appointment.setDate(dto.getDate());
        appointment.setClient(client);
        Department department = departmentRepository.getByName(dto.getDepartmentName()).orElseThrow(
                () -> new DepartmentNotFoundException("Department " + dto.getDepartmentName() + " not found")
        );
        appointment.setDepartment(department);

        appointmentRepository.save(appointment);
        String text = generateMessage(appointment, client, department);
        emailService.sendEmail(text, client.getEmail(), "Appointment data");
    }

    private static String generateMessage(Appointment appointment, Client client, Department department) {
        return "Dear " + client.getFirstName() + " " + client.getLastName() + ",\n" +
                "Thank you for making an appointment!\n"
                + "\n"
                + "Please find your appointment details below:\n" +
                "Department: " + department.getName() + "\n" +
                "Date: " + appointment.getDate() + "\n" +
                "Time: " + appointment.getTime() + "\n" +
                "Address: " + department.getAddress() + "\n" +
                "Floor: " + department.getFloor() + "\n" +
                "Kind regards\n" +
                "MedicalMate";
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

    public Map<String, Boolean> getTimeSlotsInformation(AvailableTimeSlotsRequestDto dto) {
        Map<String, Boolean> timeAvailableMap = new HashMap<>();
        for (String time : dto.getTime()) {
            Boolean timeIsNotFree = appointmentRepository.timeSlotIsNotFree(time, dto.getDate());
            timeAvailableMap.put(time, !timeIsNotFree);
        }
        return timeAvailableMap;
    }
}
