package com.appointments.service;

import com.appointments.database.entity.Client;
import com.appointments.database.repository.ClientRepository;
import com.appointments.dto.ClientResponseDto;
import com.appointments.exception.ClientNotFoundException;
import com.appointments.mapper.ClientMapper;
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

    public ClientResponseDto getClientDataByEmail(String email){
        Client clientByEmail = getClientByEmail(email);
        return ClientMapper.toClientResponseDto(clientByEmail);
    }

    public Client saveClient(Client client){
        return clientRepository.save(client);
    }


    public boolean isLoggedIn(String email){
        Client clientByEmail = getClientByEmail(email);
        return clientByEmail.isLoggedIn();
    }

    public void logout(String email){
        Client clientByEmail = getClientByEmail(email);
        clientByEmail.setLoggedIn(false);
        clientRepository.save(clientByEmail);
    }
}
