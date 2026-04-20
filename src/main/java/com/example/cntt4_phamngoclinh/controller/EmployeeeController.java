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

    private final String uploadDir = "C:\\Users\\TRONG TIN\\Downloads\\cntt4_phamngoclinh\\src\\main\\webapp\\images\\";

    @GetMapping
    public String list(@RequestParam(required = false) String keyword, Model model) {

        List<Employee> employee;

        if (keyword != null && !keyword.isEmpty()) {
            employee = repo.search(keyword);
        } else {
            employee = repo.findAll();
        }

        model.addAttribute("employee", employee);
        model.addAttribute("keyword", keyword);
        return "list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "form";
    }

    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute Employee employee,
            BindingResult result,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        if (result.hasErrors()) {
            return "form";
        }

        // upload ảnh
        if (!file.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            file.transferTo(new File(uploadDir + fileName));
            employee.setAvatar(fileName);
        } else {
            employee.setAvatar("default.png");
        }

        repo.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("employee", repo.findById(id));
        return "form";
    }

    @PostMapping("/update")
    public String update(
            @Valid @ModelAttribute Employee employee,
            BindingResult result,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        if (result.hasErrors()) {
            return "form";
        }

        if (!file.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            file.transferTo(new File(uploadDir + fileName));
            employee.setAvatar(fileName);
        }

        repo.update(employee);
        return "redirect:/employee";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        repo.delete(id);
        return "redirect:/employee";
    }
}