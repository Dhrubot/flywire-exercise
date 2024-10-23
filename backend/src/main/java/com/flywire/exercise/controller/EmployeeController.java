package com.flywire.exercise.controller;

import com.flywire.exercise.model.Employee;
import com.flywire.exercise.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
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
}
