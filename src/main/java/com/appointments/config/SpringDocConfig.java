package com.appointments.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Appointments Service API",
        version = "v1",
        description = "Reference for developers"
))
public class SpringDocConfig {

}