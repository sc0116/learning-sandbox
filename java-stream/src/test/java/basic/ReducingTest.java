package basic;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ReducingTest {

    @Test
    void reduce_메서드는_모든_스트림_요소를_처리해서_값으로_도출한다() {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        final Integer actual = numbers.stream()
                .reduce(0, Integer::sum);

        assertThat(actual).isEqualTo(15);
    }
}
