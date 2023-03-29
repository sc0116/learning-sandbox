package chapter01;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomerTest {

    @DisplayName("고객의 누적 대여료와 적립 포인트를 출력한다.")
    @Test
    void statementTest() {
        final Movie movie = new Movie("영화", 1);
        final Rental rental = new Rental(movie, 1);
        final Customer sut = new Customer("짱구");
        sut.addRental(rental);

        final String actual = sut.statement();

        assertThat(actual).isEqualTo("짱구 고객님의 대여 기록\n"
                + "\t영화\t3.0\n"
                + "누적 대여료: 3.0\n"
                + "적립 포인트: 1");
    }
}