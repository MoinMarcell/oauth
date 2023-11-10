package com.github.moinmarcell.backend.security;

import com.github.moinmarcell.backend.appuser.AppUser;
import com.github.moinmarcell.backend.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RequestMapping("/api/v1/auth")
@RestController
@AllArgsConstructor
public class AuthController {

	private final AppUserRepository appUserRepository;

	@GetMapping("/me")
	public AppUser getMe() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth instanceof OAuth2AuthenticationToken token) {
			return appUserRepository.getAppUserByGithubId(Integer.parseInt(token.getPrincipal().getName()))
					.orElseThrow(() -> new NoSuchElementException("User not found!"));
		}

		return null;
	}
}
