package com.example.cntt4_phamngoclinh.repository;

import com.example.cntt4_phamngoclinh.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class EmployeeRepository {
    private List<Employee> list = new ArrayList<>(
            Arrays.asList(
                    new Employee("NV001","Phạm Ngọc Linh","Developer",10.0,"avatar1.png"),
                    new Employee("NV001","Phạm Thị Hồng Nhung","Tester",8.0,"avatar2.png"),
                    new Employee("NV002","Nguyễn Văn Ánh","Manager",9.0,"avatar3.png")

            )
    );

    private static Integer idCounter = 4;

    public List<Employee> findAll() {
        return list;
    }

    public void save(Employee p) {
        p.setId(String.valueOf(idCounter++));
        list.add(p);
    }

    public Employee findById(String id) {
        return list.stream().filter(p ->  p.getId()
                .equals(id)).findFirst().orElse(null);
    }
    public void update(Employee employee) {

        Employee old = findById(String.valueOf(employee.getId()));
        if (old != null) {
            old.setFullName(employee.getFullName());
            old.setPosition(employee.getPosition());
            old.setSalary(employee.getSalary());
            old.setAvatar(employee.getAvatar());

        }
    }

    public void delete(String id) {
        list.removeIf(p -> p.getId().equals(id));
    }

    public List<Employee> search(String keyword) {
        return list.stream()
                .filter(p ->
                        p.getFullName().toLowerCase().contains(keyword.toLowerCase()) ||
                                p.getPosition().toLowerCase().contains(keyword.toLowerCase())
                )
                .toList();
    }


}
