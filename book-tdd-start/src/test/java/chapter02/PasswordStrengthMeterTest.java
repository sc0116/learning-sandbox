package chapter02;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PasswordStrengthMeterTest {

    private PasswordStrengthMeter sut = new PasswordStrengthMeter();

    @ValueSource(strings = {"ab12!@AB", "abc1!Add"})
    @ParameterizedTest
    void meetsAllCriteria_Then_Strong(final String password) {
        assertStrength(password, PasswordStrength.STRONG);
    }

    @ValueSource(strings = {"ab12!@A", "Ab12!c"})
    @ParameterizedTest
    void meetsOtherCriteria_except_for_Length_Then_Normal(final String password) {
        assertStrength(password, PasswordStrength.NORMAL);
    }

    @Test
    void meetsOtherCriteria_except_for_number_Then_Normal() {
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
    }

    @Test
    void nullInput_Then_Invalid() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @ValueSource(strings = {"", " ", "\t", "\n"})
    @ParameterizedTest
    void emptyInput_Then_Invalid(final String password) {
        assertStrength(password, PasswordStrength.INVALID);
    }

    @Test
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
        assertStrength("ab12!@df", PasswordStrength.NORMAL);
    }

    @Test
    void meetsOnlyLengthCriteria_Then_Weak() {
        assertStrength("abdefghi", PasswordStrength.WEAK);
    }

    @Test
    void meetsOnlyNumCriteria_Then_Weak() {
        assertStrength("12345", PasswordStrength.WEAK);
    }

    private void assertStrength(final String password, final PasswordStrength expectedStrength) {
        final PasswordStrength actual = sut.meter(password);
        assertThat(actual).isEqualTo(expectedStrength);
    }
}
