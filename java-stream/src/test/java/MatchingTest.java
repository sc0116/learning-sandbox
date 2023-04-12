import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatchingTest {

    private List<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = Arrays.asList(2, 3, 4, 5, 6);
    }

    @Test
    void anyMatch_메서드는_프레디케이트가_주어진_스트림에서_적어도_한_요소와_일치하는지_확인한다() {
        final boolean actual = numbers.stream()
                .anyMatch(number -> number == 2);

        assertThat(actual).isTrue();
    }

    @Test
    void allMatch_메서드는_스트림의_모든_요소가_주어진_프레디케이트와_일치하는지_검사한다() {
        final boolean actual = numbers.stream()
                .allMatch(number -> number < 7);

        assertThat(actual).isTrue();
    }

    @Test
    void noneMatch_메서드는_주어진_프레디케이트와_일치하는_요소가_없는지_확인한다() {
        final boolean actual = numbers.stream()
                .noneMatch(number -> number == 1);

        assertThat(actual).isTrue();
    }
}
