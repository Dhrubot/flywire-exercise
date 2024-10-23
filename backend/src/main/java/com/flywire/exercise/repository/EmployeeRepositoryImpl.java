package com.flywire.exercise.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flywire.exercise.model.Employee;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final ObjectMapper objectMapper;
    private final String employeeDataFilepath = "json/data.json";

    public EmployeeRepositoryImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Employee> findAll() {
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

    @Override
    public Employee findById(Long id) {
        return findAll().stream()
                .filter(employee -> Objects.equals(employee.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String findNameById(Long id) {
        return findById(id).getName();
    }

    @Override
    public void save(Employee employee) throws IOException {
        List<Employee> employees = findAll();

        boolean employeeExists = false;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(employee.getId())){
                employees.set(i, employee);
                employeeExists = true;
                break;
            }
        }

        if (!employeeExists){
            employees.add(employee);
        }

        objectMapper.writeValue(new File("src/main/resources/json/data.json"), employees);
    }
}
