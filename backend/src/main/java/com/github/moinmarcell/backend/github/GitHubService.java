package com.github.moinmarcell.backend.github;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GitHubService {

	private static final String GITHUB_API_BASE_URL = "https://api.github.com/user";
	private final WebClient webClient = WebClient.builder().baseUrl(GITHUB_API_BASE_URL).build();

	public GitHubUser fetchGitHubUser(String accessToken) {
		return webClient
				.get()
				.headers(headers -> headers.setBearerAuth(accessToken))
				.retrieve()
				.bodyToMono(GitHubUser.class)
				.block();
	}

}
