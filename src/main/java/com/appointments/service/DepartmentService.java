package com.appointments.service;

import com.appointments.database.entity.Department;
import com.appointments.database.repository.DepartmentRepository;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @PostConstruct
    private void generateDepartments() {
        if (!departmentRepository.existsByName("Pediatrics")) {
            Department department = new Department(1);
            department.setName("Pediatrics");
            departmentRepository.save(department);
        }
        if (!departmentRepository.existsByName("Dermatology")) {
            Department department = new Department(2);
            department.setName("Dermatology");
            departmentRepository.save(department);
        }
        if (!departmentRepository.existsByName("Ophthalmology")) {
            Department department = new Department(3);
            department.setName("Ophthalmology");
            departmentRepository.save(department);
        }
        if (!departmentRepository.existsByName("Internal Medicine")) {
            Department department = new Department(4);
            department.setName("Internal Medicine");
            departmentRepository.save(department);
        }
        if (!departmentRepository.existsByName("Surgery")) {
            Department department = new Department(5);
            department.setName("Surgery");
            departmentRepository.save(department);
        }
        if (!departmentRepository.existsByName("Neurology")) {
            Department department = new Department(6);
            department.setName("Neurology");
            departmentRepository.save(department);
        }
        if (!departmentRepository.existsByName("Gynecology")) {
            Department department = new Department(7);
            department.setName("Gynecology");
            departmentRepository.save(department);
        }
        if (!departmentRepository.existsByName("Radiology")) {
            Department department = new Department(8);
            department.setName("Radiology");
            departmentRepository.save(department);
        }
        if (!departmentRepository.existsByName("Cardiology")) {
            Department department = new Department(9);
            department.setName("Cardiology");
            departmentRepository.save(department);
        }
        if (!departmentRepository.existsByName("Urology")) {
            Department department = new Department(10);
            department.setName("Urology");
            departmentRepository.save(department);
        }
    }

    public List<String> getAllDepartmentNames() {
        return departmentRepository.findAll().stream()
                .map(Department::getName)
                .collect(Collectors.toList());
    }
}
