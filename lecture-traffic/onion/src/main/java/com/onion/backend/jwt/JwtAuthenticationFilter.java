package com.onion.backend.jwt;

import com.onion.backend.service.JwtBlacklistService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private final UserDetailsService userDetailsService;
	private final JwtBlacklistService jwtBlacklistService;

	@Override
	protected void doFilterInternal(
		final HttpServletRequest request,
		final HttpServletResponse response,
		final FilterChain filterChain
	) throws ServletException, IOException {
		final String token = resolveToken(request);

		if (Objects.nonNull(token) && jwtUtil.isValidToken(token) && !jwtBlacklistService.isTokenBlacklisted(token)) {
			final String username = jwtUtil.getUsernameFromToken(token);

			final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}

	private String resolveToken(final HttpServletRequest request) {
		final String bearerToken = request.getHeader("Authorization");

		if (Objects.nonNull(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}

		if (Objects.isNull(bearerToken)) {
			final Cookie[] cookies = request.getCookies();

			if (Objects.nonNull(cookies)) {
				final Cookie onionTokenCookie = Arrays.stream(cookies)
					.filter(cookie -> Objects.equals(cookie.getName(), "onion_token"))
					.findFirst()
					.orElse(null);

				return Objects.isNull(onionTokenCookie) ? null : onionTokenCookie.getValue();
			}
		}

		return null;
	}
}
