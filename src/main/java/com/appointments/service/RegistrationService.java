package com.appointments.service;

import com.appointments.database.entity.Client;
import com.appointments.database.repository.ClientRepository;
import com.appointments.dto.RegistrationUserDto;
import com.appointments.exception.EmailAlreadyInUseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;


    public void register(RegistrationUserDto registrationUserDto) {
        validSignUpRequest(registrationUserDto);

        Client client = createUser(registrationUserDto);
        clientRepository.save(client);

        log.info("User with email: {}, was success registered", client.getEmail());
    }

    private Client createUser(RegistrationUserDto dto) {
        Client user = new Client();

        user.setEmail(dto.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhone(dto.getPhone());

        return user;
    }


    private void validSignUpRequest(RegistrationUserDto registrationUserDto) {
        clientRepository.getByEmail(registrationUserDto.getEmail().toLowerCase()).ifPresent(user -> {
            throw new EmailAlreadyInUseException("Email " + registrationUserDto.getEmail().toLowerCase() + " already in use");
        });
    }
}
