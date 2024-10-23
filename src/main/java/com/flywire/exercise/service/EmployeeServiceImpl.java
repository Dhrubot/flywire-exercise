package com.flywire.exercise.service;

import com.flywire.exercise.model.Employee;
import com.flywire.exercise.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getActiveEmployeesSortedByLastName() {
        return employeeRepository.findAll().stream()
                .filter(Employee::isActive)
                .sorted(Comparator.comparing(employee -> {
                    String[] nameParts = employee.getName().split(" ");
                    return nameParts[nameParts.length - 1];
                }))
                .collect(Collectors.toList());
    }
}
