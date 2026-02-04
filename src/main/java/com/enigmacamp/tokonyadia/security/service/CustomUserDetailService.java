package com.enigmacamp.tokonyadia.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (!username.equals("admin")) {
            throw new UsernameNotFoundException("User not found.");
        }

        return User.builder()
                .username("admin")
                .password("$2a$10$aE.NFRUujPh3IE0rfdnftOdPUT/nnkXmnjZBSqqzQrkqK0orjE5Tm")
                .roles("ADMIN")
                .build();
    }
}
