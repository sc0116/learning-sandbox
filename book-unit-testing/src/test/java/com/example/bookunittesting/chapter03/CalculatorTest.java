package com.example.bookunittesting.chapter03;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void sum_of_two_numbers() {
        // arrange <- 주석 제거 가능
        final double first = 10;
        final double second = 20;
        final Calculator sut = new Calculator();

        // act <- 주석 제거 가능
        final double result = sut.sum(first, second);

        // assert <- 주석 제거 가능
        assertThat(result).isEqualTo(30);
    }
}
