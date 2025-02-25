package learning.basic.aop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import learning.basic.controller.HelloRequestBody;
import learning.basic.service.EncryptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PasswordEncryptionAspectTest {

	private PasswordEncryptionAspect sut;

	@Mock
	private EncryptService encryptService;

	@BeforeEach
	void setUp() {
		sut = new PasswordEncryptionAspect(encryptService);
	}

	@Test
	void test() {
		final HelloRequestBody request = new HelloRequestBody("id", "password");
		final String expected = "encryptedPassword";
		when(encryptService.encrypt(anyString())).thenReturn(expected);

		sut.fieldEncryption(request);

		assertThat(request.getPassword()).isEqualTo(expected);
	}
}