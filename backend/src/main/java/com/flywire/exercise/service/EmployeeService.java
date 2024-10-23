package com.flywire.exercise.service;

import com.flywire.exercise.model.Employee;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    List<Employee> getActiveEmployeesSortedByLastName();
    Employee getEmployeeById(Long id);
    List<String> getDirectReportsNames(Long id);
    List<Employee> getEmployeesByHiredDateRange(String startDate, String endDate) throws ParseException;
    Employee deactivateEmployee(Long id);
    Employee createEmployee(Employee employee) throws IOException;
}
