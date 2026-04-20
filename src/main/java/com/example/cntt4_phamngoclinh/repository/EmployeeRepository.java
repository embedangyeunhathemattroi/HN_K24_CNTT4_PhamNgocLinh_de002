package com.example.cntt4_phamngoclinh.repository;

import com.example.cntt4_phamngoclinh.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EmployeeRepository {

    private List<Employee> list = new ArrayList<>(
            Arrays.asList(
                    new Employee("NV001","Phạm Ngọc Linh","Developer",10.0,"avatar1.png"),
                    new Employee("NV002","Phạm Thị Hồng Nhung","Tester",8.0,"avatar2.png"),
                    new Employee("NV003","Nguyễn Văn Ánh","Manager",9.0,"avatar3.png")
            )
    );

    private static int counter = 4;

    public List<Employee> findAll() {
        return list;
    }

    public void save(Employee e) {
        e.setId("NV00" + counter++);
        list.add(e);
    }

    public Employee findById(String id) {
        return list.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void update(Employee employee) {
        Employee old = findById(employee.getId());
        if (old != null) {
            old.setFullName(employee.getFullName());
            old.setPosition(employee.getPosition());
            old.setSalary(employee.getSalary());
            old.setAvatar(employee.getAvatar());
        }
    }

    public void delete(String id) {
        list.removeIf(e -> e.getId().equals(id));
    }

    public List<Employee> search(String keyword) {
        return list.stream()
                .filter(e ->
                        e.getFullName().toLowerCase().contains(keyword.toLowerCase()) ||
                                e.getPosition().toLowerCase().contains(keyword.toLowerCase())
                )
                .toList();
    }
}