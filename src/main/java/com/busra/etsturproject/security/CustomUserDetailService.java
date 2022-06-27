package com.busra.etsturproject.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private static final User USER = new User("busra", "password", new ArrayList<>());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (USER.getUsername().equalsIgnoreCase(username)) {
            return USER;
        } else {
            return null;
        }
    }
}
