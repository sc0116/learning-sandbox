package chapter01;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class RentalTest {

    @DisplayName("최신물을 이틀 이상 대여하면 2포인트를 지급한다.")
    @ValueSource(ints = {2, 3})
    @ParameterizedTest
    void getTwoPointNewRelease(final int daysRented) {
        final Movie movie = new Movie("영화", 1);
        final Rental sut = new Rental(movie, daysRented);

        final int actual = sut.getFrequentRenterPoints();

        assertThat(actual).isEqualTo(2);
    }

    @DisplayName("그 외의 경우엔 1포인트를 지급한다.")
    @CsvSource(value = {
            "0, 1", "1, 1", "2, 1",
            "0, 2", "2, 2"
    })
    @ParameterizedTest
    void getOnePoint(final int priceCode, final int daysRented) {
        final Movie movie = new Movie("영화", priceCode);
        final Rental sut = new Rental(movie, daysRented);

        final int actual = sut.getFrequentRenterPoints();

        assertThat(actual).isEqualTo(1);
    }

    @DisplayName("비디오 종류별 대여료를 계산한다.")
    @CsvSource(value = {
            "0, 1, 2", "0, 2, 2", "0, 3, 3.5",
            "1, 1, 3", "1, 2, 6",
            "2, 1, 1.5", "2, 3, 1.5", "2, 5, 4.5"
    })
    void getCharge(final int priceCode, final int daysRented, final double expected) {
        final Movie movie = new Movie("영화", priceCode);
        final Rental sut = new Rental(movie, daysRented);

        final double actual = sut.getCharge();

        assertThat(actual).isEqualTo(expected);
    }
}