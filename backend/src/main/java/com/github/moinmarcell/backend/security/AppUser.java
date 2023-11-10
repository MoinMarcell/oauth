package com.github.moinmarcell.backend.security;

import lombok.Builder;

@Builder
public record AppUser(
        String username,
        String avatarUrl,
        int githubId
) {
}
