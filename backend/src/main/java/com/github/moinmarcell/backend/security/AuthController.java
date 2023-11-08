package com.github.moinmarcell.backend.security;

import com.github.moinmarcell.backend.appuser.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/auth")
@RestController
@AllArgsConstructor
public class AuthController {

	private final AuthService authService;

	@GetMapping("/me")
	public AppUser getMe(
			@RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient authorizedClient
	) {
		return authService.getLoggedInUser(authorizedClient);
	}

}
