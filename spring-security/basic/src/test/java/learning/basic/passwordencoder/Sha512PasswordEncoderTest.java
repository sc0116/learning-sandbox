package learning.basic.passwordencoder;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Sha512PasswordEncoderTest {

	@Test
	void sha512Test() {
		final Sha512PasswordEncoder sut = new Sha512PasswordEncoder();
		final String rawPassword = "jjanggu";
		final String encodedPassword = sut.encode(rawPassword);

		final boolean actual = sut.matches(rawPassword, encodedPassword);

		assertThat(actual).isTrue();
	}
}
