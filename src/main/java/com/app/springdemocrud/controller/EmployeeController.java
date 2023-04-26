package com.app.springdemocrud.controller;


import com.app.springdemocrud.model.Employee;
import com.app.springdemocrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "https://localhost:4200")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    // get all employees
    @PostMapping("/employees")
    public ResponseEntity<?> getAllEmployees() {
        List<Employee> list = employeeRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
//    public List<Employee> getAllEmployees() {
//        return employeeRepository.findAll();
//    }

//    create employee rest api
    @PostMapping("/employees/add")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        Employee foundEmployee = employeeRepository.findByEmailId(employee.getEmailId().trim());
        Employee addEmployee = employeeRepository.save(employee);
        return new ResponseEntity<>(addEmployee, HttpStatus.OK);
    }
}
