package com.github.moinmarcell.backend.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class AuthService {

    public AppUser getLoggedInUser() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof DefaultOAuth2User defaultOAuth2User) {
            return AppUser.builder()
                    .username(defaultOAuth2User.getAttribute("login"))
                    .avatarUrl(defaultOAuth2User.getAttribute("avatar_url"))
                    .githubId(Integer.parseInt(Objects.requireNonNull(defaultOAuth2User.getAttribute("id"))))
                    .build();
        }
        throw new IllegalStateException("User is not logged in");
    }
}
