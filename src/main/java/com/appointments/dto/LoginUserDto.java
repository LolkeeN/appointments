package com.appointments.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class LoginUserDto {

    @NotNull
    private String email;

    @NotNull
    private String password;

    @Override
    public String toString() {
        return "LoginUserDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
