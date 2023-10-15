package com.valeriygulin.service;

import com.valeriygulin.model.Admin;
import com.valeriygulin.model.Role;
import com.valeriygulin.model.Status;
import com.valeriygulin.repository.RoleRepository;
import com.valeriygulin.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepository adminRepository,
                            RoleRepository roleRepository,
                            BCryptPasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(Admin admin) {
        try {
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            List<Role> roles = roleRepository.findByNameIn(admin.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
            if (roles.isEmpty()) {
                throw new IllegalArgumentException("Roles does not exists");
            }
            admin.setRoles(roles);
            admin.setStatus(Status.ACTIVE);

            adminRepository.save(admin);

            log.info("IN register - user: {} successfully registered", admin);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Admin is already exists");
        }
    }

    @Override
    public List<Admin> getAll() {
        List<Admin> result = adminRepository.findAll();
        log.info("IN getAll - {} admins found", result.size());
        return result;
    }

    @Override
    public Admin findByUsername(String username) {
        Admin result = adminRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User does not exists"));
        log.info("IN findByUsername - admin: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public Admin get(Long id) {
        Admin result = this.adminRepository
                .findById(id).orElseThrow(
                        () -> new IllegalArgumentException("User does not exists"));

        //log.warn("IN findById - no user found by id: {}", id);
        log.info("IN findById - admin: {} found by id: {}", result);
        return result;
    }

    @Override
    public Admin delete(Long id) {
        Admin admin = this.get(id);
        this.adminRepository.deleteById(id);
        log.info("IN delete - admin with id: {} successfully deleted");
        return admin;
    }
}
