package com.valeriygulin.controllers;

import com.valeriygulin.dto.ResponseResult;
import com.valeriygulin.model.Admin;
import com.valeriygulin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseResult<Admin>> getAdminById(@PathVariable(name = "id") Long id) {
        try {
            Admin admin = adminService.get(id);
            return new ResponseEntity<>(
                    new ResponseResult<>(null, admin), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseResult<Admin>> add(@RequestBody Admin admin) {
        try {
            this.adminService.register(admin);
            return new ResponseEntity<>(new ResponseResult<>(null, admin), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Admin>> delete(@PathVariable long id) {
        try {
            Admin admin = this.adminService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, admin), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
