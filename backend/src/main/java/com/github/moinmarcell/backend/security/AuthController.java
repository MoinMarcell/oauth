package com.github.moinmarcell.backend.security;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/auth")
@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/me")
    public AppUser getMe() {
        return authService.getLoggedInUser();
    }

}
