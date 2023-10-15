package com.valeriygulin.security;

import com.valeriygulin.model.Admin;
import com.valeriygulin.repository.AdminRepository;
import com.valeriygulin.security.jwt.JwtUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    private final AdminRepository adminRepository;

    public JwtUserDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<Admin> admin = adminRepository.findByUserName(userName);

        admin.orElseThrow(() -> new UsernameNotFoundException("User with username: " + userName + " not found"));

        log.info("IN loadUserByUsername - user with username: {} successfully loaded", userName);
        return admin.map(JwtUser::new).get();
    }
}
