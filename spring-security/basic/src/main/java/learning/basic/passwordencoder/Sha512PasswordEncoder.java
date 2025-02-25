package learning.basic.passwordencoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha512PasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(final CharSequence rawPassword) {
		return hashWithSHA512(rawPassword.toString());
	}

	@Override
	public boolean matches(final CharSequence rawPassword, final String encodedPassword) {
		final String hashedPassword = encode(rawPassword);
		return encodedPassword.equals(hashedPassword);
	}

	private String hashWithSHA512(final String password) {
		final StringBuilder sb = new StringBuilder();

		try {
			final MessageDigest md = MessageDigest.getInstance("SHA-512");
			final byte[] digest = md.digest(password.getBytes());
			for (final byte b : digest) {
				sb.append(Integer.toHexString(0xFF & b));
			}
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("invalid algorithm");
		}

		return sb.toString();
	}
}
