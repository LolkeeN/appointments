package com.appointments.service;

import com.appointments.database.entity.Client;
import com.appointments.dto.ClientResponseDto;
import com.appointments.dto.LoginUserDto;
import com.appointments.exception.InvalidPasswordException;
import com.appointments.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;


    public ClientResponseDto auth(LoginUserDto dto) {
        Client client = clientService.getClientByEmail(dto.getEmail().toLowerCase());

        if (!passwordEncoder.matches(dto.getPassword(), client.getPassword())){
            throw new InvalidPasswordException("Invalid password for user " + dto.getEmail());
        }

        return ClientMapper.toClientResponseDto(client);
    }
}
