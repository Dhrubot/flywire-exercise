package com.flywire.exercise.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Employee {
    private Long id;
    private String name;
    private String position;
    private String hireDate;
    private boolean active;
    private List<Long> directReports;
}
