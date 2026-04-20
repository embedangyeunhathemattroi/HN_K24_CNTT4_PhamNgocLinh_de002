package com.example.cntt4_phamngoclinh.model;
public class Employee {
    private String id;
    //bat buoc phai nhap, do dai tu 5 den 50 ky tu

    private String fullName;

    private String position; //vi tri/chuc vu

    private Double salary;
    private String avatar; //duong dan ten file hoac anh dai dien

    public Employee(){}

    public Employee(String id, String fullName, String position, Double salary, String avatar) {
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
