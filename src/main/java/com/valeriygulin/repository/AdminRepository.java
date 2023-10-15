package com.valeriygulin.repository;

import com.valeriygulin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByLoginAndPassword(String login, String password);

    Optional<Admin> findByUserName(String userName);
}
