package com.springboot.cache.controller;

import com.springboot.cache.bean.Employee;
import com.springboot.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/getById")
    public Employee getEmployeeById(int id){
        return employeeService.getEmpById(id);
    }
    @GetMapping("/updateEmp")
    public Employee updateEmp(Employee employee){
        return employeeService.updateEmp(employee);
    }

    @GetMapping("/deleteEmpById")
    public String deleteEmpById(int id){
        employeeService.deleteEmpById(id);
        return "success";
    }

    @GetMapping("/getEmpByLastName")
    public Employee getEmpByLastName(String lastName){
        return employeeService.getEmoByLastName(lastName);
    }
}
