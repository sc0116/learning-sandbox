package com.onion.backend.service;

import com.onion.backend.dto.SignUpUser;
import com.onion.backend.entity.User;
import com.onion.backend.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public User create(final SignUpUser signUpUser) {
		final User user = new User(
			signUpUser.username(),
			passwordEncoder.encode(signUpUser.password()),
			signUpUser.email()
		);

		return userRepository.save(user);
	}

	public List<User> readAll() {
		return userRepository.findAll();
	}

	public void delete(final Long id) {
		userRepository.deleteById(id);
	}
}
