package chapter03;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.time.LocalDate;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ExpirationDateCalculatorTest {

    @MethodSource("argumentProvider1")
    @ParameterizedTest
    void 만원_납부하면_한달_뒤가_만료일이_된다(final LocalDate billingDate, final LocalDate expected) {
        assertExpirationDate(new PayData(billingDate, 10_000), expected);
    }

    private static Stream<Arguments> argumentProvider1() {
        return Stream.of(
                arguments(LocalDate.of(2023, 3, 22), LocalDate.of(2023, 4, 22)),
                arguments(LocalDate.of(2023, 5, 5), LocalDate.of(2023, 6, 5))
        );
    }

    @MethodSource("argumentProvider2")
    @ParameterizedTest
    void 납부일과_한달_뒤_일자가_같지_않다(final LocalDate billingDate, final LocalDate expected) {
        assertExpirationDate(new PayData(billingDate, 10_000), expected);
    }

    private static Stream<Arguments> argumentProvider2() {
        return Stream.of(
                arguments(LocalDate.of(2023, 1, 31), LocalDate.of(2023, 2, 28)),
                arguments(LocalDate.of(2023, 5, 31), LocalDate.of(2023, 6, 30))
        );
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부() {
        assertExpirationDate(
                new PayData(
                        LocalDate.of(2023, 1, 31),
                        LocalDate.of(2023, 2, 28),
                        10_000
                ),
                LocalDate.of(2023, 3, 31)
        );
    }

    @Test
    void 이만원_이상_납부하면_비례해서_만료일_계산() {
        assertExpirationDate(
                new PayData(
                        LocalDate.of(2023, 3, 1),
                        20_000
                ),
                LocalDate.of(2023, 5, 1)
        );
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부() {
        assertExpirationDate(
                new PayData(
                        LocalDate.of(2023, 1, 31),
                        LocalDate.of(2023, 2, 28),
                        20_000
                ),
                LocalDate.of(2023, 4, 30)
        );
    }

    @Test
    void 십만원을_납부하면_1년_제공() {
        assertExpirationDate(
                new PayData(
                        LocalDate.of(2023, 1, 28),
                        100_000
                ),
                LocalDate.of(2024, 1, 28)
        );
    }

    private void assertExpirationDate(final PayData payData, final LocalDate expected) {
        final ExpirationDateCalculator sut = new ExpirationDateCalculator();

        final LocalDate expirationDate = sut.calculateExpirationDate(payData);

        assertThat(expirationDate).isEqualTo(expected);
    }
}
