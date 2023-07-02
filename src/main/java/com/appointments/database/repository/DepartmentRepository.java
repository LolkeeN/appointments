package com.appointments.database.repository;

import com.appointments.database.entity.Department;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {

    boolean existsByName(String name);
    Optional<Department> getByName(String name);
}
