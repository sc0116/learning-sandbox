package com.onion.backend.repository;

import com.onion.backend.entity.JwtBlacklist;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JwtBlacklistRepository extends JpaRepository<JwtBlacklist, Long> {

	Optional<JwtBlacklist> findByToken(String token);

	@Query(value = """
		SELECT jb
		FROM JwtBlacklist jb
		WHERE jb.username = :username
		ORDER BY jb.expirationTime
		LIMIT 1"""
	)
	Optional<JwtBlacklist> findByUsernameOrderByExpirationTime(String username);
}
