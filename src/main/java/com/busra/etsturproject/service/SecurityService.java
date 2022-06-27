package com.busra.etsturproject.service;

import com.busra.etsturproject.dto.LoginRequest;
import com.busra.etsturproject.security.CustomUserDetailService;
import com.busra.etsturproject.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SecurityService {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailService userDetailsService;

    public String login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new RuntimeException("Not valid username or password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

        return jwtUtil.generateToken(userDetails);
    }
}
