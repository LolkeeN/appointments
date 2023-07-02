package com.appointments.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Client {

    @Id
    private String id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column(unique = true)
    private String phone;
    private String avatarColor;
    @OneToMany(mappedBy = "client")
    private List<Appointment> appointments;

    public Client() {
        this.id = UUID.randomUUID().toString();
    }
}
