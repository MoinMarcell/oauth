package com.github.moinmarcell.backend.security;

import com.github.moinmarcell.backend.appuser.AppUser;
import com.github.moinmarcell.backend.appuser.AppUserService;
import com.github.moinmarcell.backend.github.GitHubService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

	private final AppUserService appUserService;
	private final GitHubService gitHubService;

	public AppUser getLoggedInUser(OAuth2AuthorizedClient authorizedClient) {
		var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String accessToken = authorizedClient.getAccessToken().getTokenValue();
		if (principal instanceof DefaultOAuth2User) {
			return appUserService.saveOrGetAppUser(gitHubService.fetchGitHubUser(accessToken));
		}
		throw new IllegalArgumentException("Nope!");
	}

}
