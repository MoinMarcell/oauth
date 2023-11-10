package com.github.moinmarcell.backend.security;

import com.github.moinmarcell.backend.appuser.AppUser;
import com.github.moinmarcell.backend.appuser.AppUserRepository;
import com.github.moinmarcell.backend.appuser.AppUserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final AppUserRepository appUserRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);

        AppUser githubUser = appUserRepository
                .getAppUserByGithubId(Integer.parseInt(user.getName()))
                .orElseGet(() -> {
                    AppUser newUser = AppUser.builder()
                            .username((String) user.getAttributes().get("login"))
                            .registrationDate(ZonedDateTime.now().toString())
                            .githubId((Integer) user.getAttributes().get("id"))
                            .role(AppUserRole.USER)
                            .build();

                    return appUserRepository.save(newUser);
                });

        return new DefaultOAuth2User(List.of(new SimpleGrantedAuthority(githubUser.role().toString())), user.getAttributes(), "id");
    }
}
