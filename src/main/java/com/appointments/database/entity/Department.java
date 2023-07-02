package com.appointments.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
public class Department {

    @Id
    private String id;
    private String name;
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int floor;
    @Column
    private String address = "123 Main Street New York USA 14489";

    public Department(int floor) {
        this.id = UUID.randomUUID().toString();
        this.floor = floor;
    }

    public Department() {
        this.id = UUID.randomUUID().toString();
    }
}
