package collect;

import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SummarizationTest {

    private List<Dish> menu;

    @BeforeEach
    void setUp() {
        menu = Arrays.asList(
                new Dish("season fruit", true, 120, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("rice", true, 350, Type.OTHER)
        );
    }

    @Test
    void joining_메서드는_스트림의_각_객체에_toString_메서드를_호출해서_추출한_모든_문자열을_하나의_문자열로_연결해서_반환한다() {
        final String actual = menu.stream()
                .map(Dish::toString)
                .collect(joining(", "));

        assertThat(actual).isEqualTo("season fruit, prawns, rice");
    }
}
