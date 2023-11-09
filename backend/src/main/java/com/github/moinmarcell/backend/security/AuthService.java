package com.github.moinmarcell.backend.security;

import com.github.moinmarcell.backend.appuser.AppUser;
import com.github.moinmarcell.backend.appuser.AppUserService;
import com.github.moinmarcell.backend.github.GitHubService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

	private final AppUserService appUserService;
	private final GitHubService gitHubService;

	public AppUser getLoggedInUser(OAuth2AuthorizedClient authorizedClient) {
		String accessToken = authorizedClient.getAccessToken().getTokenValue();
		return appUserService.saveOrGetAppUser(gitHubService.fetchGitHubUser(accessToken));
	}
}
