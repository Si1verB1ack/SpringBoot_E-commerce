package com.example.demo.security;

import com.example.demo.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class MyUserDetails implements UserDetails {

    private final User user;

    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = (user.getRole() == 1) ? "ROLE_ADMIN" : "ROLE_USER";
        System.out.println("User role: " + role); // Debugging line
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }


    @Override
    public String getPassword() {
        return user.getPassword(); // Return the actual password
    }

    @Override
    public String getUsername() {
        return user.getUsername(); // Return the actual username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Or implement actual logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Or implement actual logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Or implement actual logic
    }

    @Override
    public boolean isEnabled() {
        return true; // Or implement actual logic
    }
}
