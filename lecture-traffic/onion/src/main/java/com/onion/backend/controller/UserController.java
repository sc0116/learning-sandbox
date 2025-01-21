package com.onion.backend.controller;

import com.onion.backend.dto.SignUpUser;
import com.onion.backend.entity.User;
import com.onion.backend.jwt.JwtUtil;
import com.onion.backend.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/api/users")
@RequiredArgsConstructor
@RestController
public class UserController {

	private final UserService userService;
	private final UserDetailsService userDetailService;
	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;

	@PostMapping("/sign-up")
	public ResponseEntity<User> create(
		@RequestBody final SignUpUser signUpUser
		) {
		final User user = userService.create(signUpUser);

		return ResponseEntity.ok(user);
	}

	@PostMapping("/login")
	public String login(@RequestParam final String username, @RequestParam final String password) throws AuthenticationException {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		final UserDetails userDetails = userDetailService.loadUserByUsername(username);

		return jwtUtil.generateToken(userDetails.getUsername());
	}

	@PostMapping("/token/validation")
	public ResponseEntity<Void> validateToken(@RequestParam final String token) {
		if (!jwtUtil.isValidToken(token)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This username is not allowed");
		}

		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<List<User>> findUsers() {
		return ResponseEntity.ok(userService.readAll());
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> delete(
		@PathVariable final Long userId
	) {
		userService.delete(userId);

		return ResponseEntity.noContent().build();
	}
}
