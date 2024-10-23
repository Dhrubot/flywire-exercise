package com.flywire.exercise.service;

import com.flywire.exercise.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    List<Employee> getActiveEmployeesSortedByLastName();
    Employee getEmployeeById(Long id);
    List<String> getDirectReportsNames(Long id);
}
