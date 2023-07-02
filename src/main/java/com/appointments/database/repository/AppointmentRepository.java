package com.appointments.database.repository;

import com.appointments.database.entity.Appointment;
import com.appointments.database.entity.enums.AppointmentType;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> getAllByClientId(String clientId);
    List<Appointment> findAllByAppointmentType(AppointmentType appointmentType);

    @Query(value = "select"
            + " exists("
            + " select c from Client c where"
            + " (select a from Appointment a where a.time = :time and a.date = :date)"
            + " member of c.appointments)")
    Boolean timeSlotIsNotFree(String time, LocalDate date);
}
