package com.onion.backend.controller;

import com.onion.backend.entity.User;
import com.onion.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/users")
@RequiredArgsConstructor
@RestController
public class UserController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<User> create(
		@RequestParam final String username,
		@RequestParam final String password,
		@RequestParam final String email
	) {
		final User user = userService.create(username, password, email);

		return ResponseEntity.ok(user);
	}
}
