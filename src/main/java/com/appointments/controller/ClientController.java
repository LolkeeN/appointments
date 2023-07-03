package com.appointments.controller;

import com.appointments.dto.ClientResponseDto;
import com.appointments.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/{clientEmail}")
    public ResponseEntity<ClientResponseDto> clientResponseDto(@PathVariable String clientEmail){
        ClientResponseDto responseDto = clientService.getClientDataByEmail(clientEmail);
        return ResponseEntity.ok(responseDto);
    }
}
