package com.onion.backend.service;

import com.onion.backend.entity.JwtBlacklist;
import com.onion.backend.jwt.JwtUtil;
import com.onion.backend.repository.JwtBlacklistRepository;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtBlacklistService {

	private final JwtBlacklistRepository jwtBlacklistRepository;
	private final JwtUtil jwtUtil;

	public void blacklistToken(final String username, final String token, final LocalDateTime expirationTime) {
		final JwtBlacklist jwtBlacklist = new JwtBlacklist(username, token, expirationTime);

		jwtBlacklistRepository.save(jwtBlacklist);
	}

	public boolean isTokenBlacklisted(final String token) {
		final String username = jwtUtil.getUsernameFromToken(token);
		final Optional<JwtBlacklist> jwtBlacklist = jwtBlacklistRepository.findByUsernameOrderByExpirationTime(username);

		if (jwtBlacklist.isEmpty()) {
			return false;
		}

		final Instant instant = jwtUtil.getExpirationDateFromToken(token).toInstant();
		final LocalDateTime currentTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

		return jwtBlacklist.get().getExpirationTime().minusHours(1).isAfter(currentTime);
	}
}
