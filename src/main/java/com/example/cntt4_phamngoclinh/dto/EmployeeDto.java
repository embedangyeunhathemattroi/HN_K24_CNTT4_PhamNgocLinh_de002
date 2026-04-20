package com.example.cntt4_phamngoclinh.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EmployeeDto {
    @NotBlank(message = "ID không được trống")
    private String id;

    @NotBlank(message = "Full name không được trống")
    @Size(min = 5, max = 50)
    private String fullName;

    @NotBlank(message = "Position không được trống")
    private String position;

    @NotNull(message = "Salary không được trống")
    @Min(value = 1, message = "Salary phải > 0")
    private Double salary;

    private String avatar;


    public EmployeeDto(String id, String fullName, String position, Double salary, String avatar) {
        this.id = id;
        this.fullName = fullName;
        this.position = position;
        this.salary = salary;
        this.avatar = avatar;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
