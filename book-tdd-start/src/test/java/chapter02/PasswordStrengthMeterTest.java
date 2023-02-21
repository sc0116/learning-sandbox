package chapter02;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
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

    @ValueSource(strings = {"ab12!@A", "Ab12!c"})
    @ParameterizedTest
    void meetsOtherCriteria_except_for_Length_Then_Normal(final String password) {
        final PasswordStrengthMeter sut = new PasswordStrengthMeter();

        final PasswordStrength actual = sut.meter(password);

        assertThat(actual).isEqualTo(PasswordStrength.NORMAL);
    }

    @Test
    void meetsOtherCriteria_except_for_number_Then_Normal() {
        final PasswordStrengthMeter sut = new PasswordStrengthMeter();

        final PasswordStrength actual = sut.meter("ab!@ABqwer");

        assertThat(actual).isEqualTo(PasswordStrength.NORMAL);
    }
}
