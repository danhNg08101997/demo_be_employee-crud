package com.app.springdemocrud.controller;


import com.app.springdemocrud.exception.ResourceNotFoundException;
import com.app.springdemocrud.model.Employee;
import com.app.springdemocrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "https://localhost:4200")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    // get all employees
    @PostMapping("/employees")
    //    public List<Employee> getAllEmployees() {
    //        return employeeRepository.findAll();
    //    }
    public ResponseEntity<?> getAllEmployees() {
        List<Employee> list = employeeRepository.findAll();
        //        return new ResponseEntity<>(list, HttpStatus.OK);
        return ResponseEntity.ok(list);
    }

//    create employee rest api
    @PostMapping("/employees/add")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        Employee foundEmployee = employeeRepository.findByEmailId(employee.getEmailId().trim());
        Employee addEmployee = employeeRepository.save(employee);
        //        return new ResponseEntity<>(addEmployee, HttpStatus.OK);
        return ResponseEntity.ok(addEmployee);
    }

    // Get employee by id rest api
    @PostMapping("/employees/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not exits with id: " + id));
//        return new ResponseEntity<>(HttpStatus.OK);
        return ResponseEntity.ok(employee);
    }

    // Update employee rest api
    @PostMapping("/employees/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exits with id: " + id));
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        Employee updateEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updateEmployee);
    }

    // Delete employee rest api
    @PostMapping("/employees/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exits with id: " + id));
        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
