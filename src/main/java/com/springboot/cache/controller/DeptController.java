package com.springboot.cache.controller;

import com.springboot.cache.bean.Department;
import com.springboot.cache.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {

    @Autowired
    DeptService deptService;

    @GetMapping("/getDeptById")
    public Department getDeptById(int id){
        return deptService.getDeptBtId(id);
    }
}
