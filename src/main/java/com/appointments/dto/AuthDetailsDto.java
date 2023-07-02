package com.appointments.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDetailsDto {

    private String userId;

    public AuthDetailsDto(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AuthDetailsDto{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
