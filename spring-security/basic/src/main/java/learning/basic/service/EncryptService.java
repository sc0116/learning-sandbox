package learning.basic.service;

import org.springframework.stereotype.Service;

@Service
public class EncryptService {

	public String encrypt(final String password) {
		return "encrypted_" + password;
	}
}
