package com.appointments.controller;

import com.appointments.dto.AuthDetailsDto;
import com.appointments.dto.ClientResponseDto;
import com.appointments.dto.LoginUserDto;
import com.appointments.service.AuthService;
import com.appointments.service.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponseDto> login(@Valid @RequestBody LoginUserDto dto, HttpServletRequest request) {
        log.debug("User made login request");
        ClientResponseDto responseDto = authService.auth(dto);
        return ResponseEntity.ok(responseDto);
    }
}
