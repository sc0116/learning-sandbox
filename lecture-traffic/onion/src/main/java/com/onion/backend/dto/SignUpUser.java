package com.onion.backend.dto;

public record SignUpUser(
	String username,
	String password,
	String email
) {
}
