package com.github.moinmarcell.backend.appuser;

import com.github.moinmarcell.backend.github.GitHubUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@AllArgsConstructor
public class AppUserService {

	private final AppUserRepository appUserRepository;

	public AppUser saveOrGetAppUser(GitHubUser gitHubUser) {
		int githubId = gitHubUser.id();
		if (existsAppUserByGithubId(githubId)) {
			return getAppUserByGithubId(githubId);
		} else {
			AppUser appUser = AppUser.builder()
					.username(gitHubUser.login())
					.registrationDate(ZonedDateTime.now().toString())
					.githubId(githubId)
					.role(AppUserRole.USER)
					.build();
			return saveAppUser(appUser);
		}
	}

	private AppUser saveAppUser(AppUser appUser) {
		return appUserRepository.save(appUser);
	}

	private AppUser getAppUserByGithubId(int githubId) {
		return appUserRepository.getAppUserByGithubId(githubId).orElseThrow();
	}

	private boolean existsAppUserByGithubId(int githubId) {
		return appUserRepository.existsAppUserByGithubId(githubId);
	}

}
