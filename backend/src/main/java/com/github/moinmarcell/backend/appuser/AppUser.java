package com.github.moinmarcell.backend.appuser;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Builder
public record AppUser(
		String id,
		String username,
		String registrationDate,
		AppUserRole role,
		int githubId
) {
}
