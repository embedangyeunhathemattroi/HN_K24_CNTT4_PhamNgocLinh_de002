package com.example.cntt4_phamngoclinh.controller;

import com.example.cntt4_phamngoclinh.model.Employee;
import com.example.cntt4_phamngoclinh.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/employee")
public class EmployeeeController {

    private final EmployeeRepository repo = new EmployeeRepository();

    private final String uploadDir = "C:/uploads/";

    // LIST + SEARCH
    @GetMapping
    public String list(
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model
    ) {

        List<Employee> employees;

        if (keyword != null && !keyword.isEmpty()) {
            employees = repo.search(keyword);
        } else {
            employees = repo.findAll();
        }

        model.addAttribute("employees", employees);
        model.addAttribute("keyword", keyword);
        return "list";
    }

    // CREATE FORM
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "form";
    }

    // SAVE
    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute("employee") Employee employee,
            BindingResult result,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        if (result.hasErrors()) {
            return "form";
        }

        handleUpload(employee, file);

        repo.save(employee);
        return "redirect:/employee";
    }

    // EDIT
    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") String id,
            Model model
    ) {
        model.addAttribute("employee", repo.findById(id));
        return "form";
    }

    // UPDATE
    @PostMapping("/update")
    public String update(
            @Valid @ModelAttribute("employee") Employee employee,
            BindingResult result,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        if (result.hasErrors()) {
            return "form";
        }

        if (!file.isEmpty()) {
            handleUpload(employee, file);
        }

        repo.update(employee);
        return "redirect:/employee";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        repo.delete(id);
        return "redirect:/employee";
    }

    // UPLOAD COMMON METHOD
    private void handleUpload(Employee employee, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            file.transferTo(new File(uploadDir + fileName));
            employee.setAvatar(fileName);
        } else {
            employee.setAvatar("default.png");
        }
    }
}