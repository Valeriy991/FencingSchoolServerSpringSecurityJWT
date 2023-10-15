package com.valeriygulin.service;

import com.valeriygulin.model.Admin;

import java.util.List;

public interface AdminService {
    void register(Admin admin);

    List<Admin> getAll();

    Admin findByUsername(String username);

    Admin get(Long id);



    Admin delete(Long id);
}
