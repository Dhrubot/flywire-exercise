package com.flywire.exercise.repository;

import com.flywire.exercise.model.Employee;

import java.io.IOException;
import java.util.List;

public interface EmployeeRepository {
    List<Employee> findAll();
    Employee findById(Long id);
    String findNameById(Long id);
    void save(Employee employee) throws IOException;
}
