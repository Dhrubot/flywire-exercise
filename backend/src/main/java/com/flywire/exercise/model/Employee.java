package com.flywire.exercise.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Employee {
    private Long id;
    @NotEmpty(message = "Name is required")
    private String name;
    @NotEmpty(message = "Position is required")
    private String position;
    private String hireDate;
    private boolean active;
    private List<Long> directReports;

    @JsonIgnore
    public Date getParsedHireDate() {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
            return dateFormatter.parse(hireDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
