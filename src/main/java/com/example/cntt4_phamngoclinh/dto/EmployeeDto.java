package com.example.cntt4_phamngoclinh.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmployeeDto {
    @NotBlank(message = "bat buoc phai nhap ten ")
    @Size(min = 5,max = 50,message = "do dai cho phep tu 5 den 50 ky tu")
    private String id;
    @NotBlank(message = "Bat buoc phai nhap vi tri , ko duoc de trong ")
    private String fullName;
    @Size(min = 1,message = "Muc luong phai khong the am va lon hon 0")
    private String position;
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
