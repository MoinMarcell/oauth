package com.github.moinmarcell.backend.appuser;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AppUserRepository extends MongoRepository<AppUser, String> {

	Optional<AppUser> getAppUserByGithubId(int githubId);
}
