package com.valeriygulin.service;

import com.valeriygulin.model.Role;
import com.valeriygulin.repository.RoleRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void add(Role role){
        try {
            this.roleRepository.save(role);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Role is already exists!");
        }
    }
}
