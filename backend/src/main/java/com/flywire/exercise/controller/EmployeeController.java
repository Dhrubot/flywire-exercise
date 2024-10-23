package com.flywire.exercise.controller;

import com.flywire.exercise.model.Employee;
import com.flywire.exercise.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = {"https://flywire-emplyee-management.surge.sh", "http://localhost:5173", "http://127.0.0.1:5173"})
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/active")
    public  List<Employee> getActiveEmployeesSorted() {
        return employeeService.getActiveEmployeesSortedByLastName();
    }

    @GetMapping("/sorted-by-hireDate")
    public List<Employee> getEmployeesByHireDateRange(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) throws ParseException {
        return employeeService.getEmployeesByHiredDateRange(startDate, endDate);
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateEmployee(@PathVariable Long id) {
        Employee deactivatedEmployee = employeeService.deactivateEmployee(id);
        if (deactivatedEmployee != null) {
            return ResponseEntity.ok("Employee with ID " + id + " has been deactivated.");
        } else {
            return ResponseEntity.badRequest().body("Employee not found or already inactive.");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createEmployee(@RequestBody Employee employee) {
        try {
            Employee newEmployee = employeeService.createEmployee(employee);

            return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating employee: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating employee: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmployeeDetailsWithDirectReportsById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);

        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        List<String> directReportNames = employeeService.getDirectReportsNames(id);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("id", employee.getId());
        responseBody.put("name", employee.getName());
        responseBody.put("position", employee.getPosition());
        responseBody.put("hireDate", employee.getHireDate());
        responseBody.put("active", employee.isActive());
        responseBody.put("directReports", directReportNames);


        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

}
