package com.valeriygulin.controllers;

import com.valeriygulin.model.Admin;
import com.valeriygulin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

    private final AdminService adminService;

    @Autowired
    public AdminRestControllerV1(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<Admin> getUserById(@PathVariable(name = "id") Long id) {
        Admin admin = adminService.get(id);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }
}
