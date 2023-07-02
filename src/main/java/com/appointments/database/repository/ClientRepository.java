package com.appointments.database.repository;

import com.appointments.database.entity.Client;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {

    Optional<Client> getByEmail(String email);
}
