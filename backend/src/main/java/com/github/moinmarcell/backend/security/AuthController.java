package com.github.moinmarcell.backend.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/auth")
@RestController
@AllArgsConstructor
public class AuthController {

	@GetMapping("/me")
	public String getMe() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth instanceof OAuth2AuthenticationToken token) {
			return token.getPrincipal().getAttributes().get("login").toString();
		}

		return auth.getName();
	}

}
