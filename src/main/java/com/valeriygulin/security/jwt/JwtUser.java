package com.valeriygulin.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.valeriygulin.model.Account;
import com.valeriygulin.model.Status;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Data
public class JwtUser implements UserDetails {
    @JsonIgnore
    private final Long id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final boolean enabled;
    @JsonIgnore
    private final Date lastPasswordResetDate;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(Account account) {
        this.id = account.getId();
        this.username = account.getUserName();
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        this.password = account.getPassword();
        this.authorities = account.getRoles().stream()
                .map(x->new SimpleGrantedAuthority(x.getName()))
                .collect(Collectors.toList());;
        this.enabled = account.getStatus().equals(Status.ACTIVE);
        this.lastPasswordResetDate = account.getUpdated();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
