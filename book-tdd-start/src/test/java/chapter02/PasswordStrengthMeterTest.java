package chapter02;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PasswordStrengthMeterTest {

    @ValueSource(strings = {"ab12!@AB", "abc1!Add"})
    @ParameterizedTest
    void meetsAllCriteria_Then_Strong(final String password) {
        final PasswordStrengthMeter sut = new PasswordStrengthMeter();

        PasswordStrength actual = sut.meter(password);

        assertThat(actual).isEqualTo(PasswordStrength.STRONG);
    }
}
