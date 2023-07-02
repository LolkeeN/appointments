package com.appointments.database.repository;

import com.appointments.database.entity.Appointment;
import com.appointments.database.entity.enums.AppointmentType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> getAllByClientId(String clientId);
    List<Appointment> findAllByAppointmentType(AppointmentType appointmentType);
}
