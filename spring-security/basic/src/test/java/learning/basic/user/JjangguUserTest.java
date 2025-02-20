package learning.basic.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

class JjangguUserTest {

	@Test
	void test() {
		final JjangguUser sut = new JjangguUser();

		assertAll(
			() -> assertThat(sut.getUsername()).isEqualTo("jjanggu.shin"),
			() -> assertThat(sut.getPassword()).isEqualTo("12345"),
			() -> {
				final GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("READ");

				assertThat(sut.getAuthorities()).usingRecursiveComparison()
					.isEqualTo(List.of(grantedAuthority));
			}
		);
	}
}
