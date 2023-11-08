package com.github.moinmarcell.backend.github;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GitHubUser(
		int id,
		String login,
		String name,
		String email,
		@JsonProperty("avatar_url")
		String avatarUrl
) {
}
