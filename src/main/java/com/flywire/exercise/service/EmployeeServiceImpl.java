package com.flywire.exercise.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flywire.exercise.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final ObjectMapper objectMapper;
    private final String employeeDataFilepath = "json/data.json";

    @Autowired
    public EmployeeServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Employee> getAllEmployees() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(employeeDataFilepath)) {
            if (inputStream == null) {
                throw new FileNotFoundException("File not found in resources: " + employeeDataFilepath);
            }
            Employee[] employees = objectMapper.readValue(inputStream, Employee[].class);
            return Arrays.asList(employees);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
