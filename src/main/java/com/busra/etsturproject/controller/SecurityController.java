package com.busra.etsturproject.controller;

import com.busra.etsturproject.dto.LoginRequest;
import com.busra.etsturproject.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("security")
@RequiredArgsConstructor
public class SecurityController {

    private final SecurityService securityService;

    @PostMapping("/login")
    public String creteToken(@RequestBody LoginRequest loginRequest) throws Exception {
        return securityService.login(loginRequest);
    }

}
