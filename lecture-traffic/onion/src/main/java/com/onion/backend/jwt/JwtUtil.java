package com.onion.backend.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

	private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private static final long EXPIRATION_TIME = 3_600_000;

	public String generateToken(final String username) {
		final Date now = new Date();
		final Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);

		return Jwts.builder()
			.setSubject(username)
			.setIssuedAt(now)
			.setExpiration(expirationDate)
			.signWith(SECRET_KEY)
			.compact();
	}

	public boolean isValidToken(final String token) {
		try {
			getJwtParser().parseClaimsJws(token);

			return true;
		} catch (JwtException e) {
			return false;
		}
	}

	public String getUsernameFromToken(final String token) {
		return getJwtParser().parseClaimsJws(token)
			.getBody()
			.getSubject();
	}

	public Date getExpirationDateFromToken(final String token) {
		return getJwtParser().parseClaimsJws(token)
			.getBody()
			.getExpiration();
	}

	private JwtParser getJwtParser() {
		return Jwts.parserBuilder()
			.setSigningKey(SECRET_KEY)
			.build();
	}
}
