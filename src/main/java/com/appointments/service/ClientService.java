package com.appointments.service;

import com.appointments.database.entity.Client;
import com.appointments.database.repository.ClientRepository;
import com.appointments.exception.ClientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client getClientByEmail(String email){
        return clientRepository.getByEmail(email).orElseThrow(
                () -> new ClientNotFoundException("Client not found for email " + email)
        );
    }
}
