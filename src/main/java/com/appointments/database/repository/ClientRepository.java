package com.appointments.database.repository;

import com.appointments.database.entity.Client;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, String> {

    Optional<Client> getByEmail(String email);
}
