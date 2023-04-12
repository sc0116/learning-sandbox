import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MappingTest {

    private List<String> words;

    @BeforeEach
    void setUp() {
        words = Arrays.asList("Hello", "World");
    }

    @Test
    void map_메서드는_각_요소에_인수로_제공된_함수를_적용한_결과가_새로운_요소로_매핑된다() {
        final List<Integer> actual = words.stream()
                .map(String::length)
                .collect(Collectors.toList());

        assertThat(actual).hasSize(2)
                .containsExactly(5, 5);
    }

    @Test
    void flatMap_메서드는_스트림의_각_값을_다른_스트림으로_만든_다음에_모든_스트림을_하나의_스트림으로_연결하는_기능을_수행한다() {
        final List<String> actual = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        assertThat(actual).hasSize(7)
                .containsExactly("H", "e", "l", "o", "W", "r", "d");
    }
}
