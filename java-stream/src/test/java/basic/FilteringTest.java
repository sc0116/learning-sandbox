package basic;

import static org.assertj.core.api.Assertions.assertThat;

import basic.Dish;
import basic.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilteringTest {

    private List<Dish> menu;

    @BeforeEach
    void setUp() {
        menu = Arrays.asList(
                new Dish("pork", false, 800, Type.MEAT),
                new Dish("beef", false, 700, Type.MEAT),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french", true, 530, Type.OTHER),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("season fruit", true, 120, Type.OTHER),
                new Dish("pizza", true, 550, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("salmon", false, 450, Type.FISH)
        );
    }

    @Test
    void filter_메서드는_프레디케이트를_인수로_받아서_프레디케이트와_일치하는_모든_요소를_포함하는_스트림을_반환한다() {
        final List<String> actual = menu.stream()
                .filter(Dish::isVegetarian)
                .map(Dish::toString)
                .collect(Collectors.toList());

        assertThat(actual).hasSize(4)
                .containsExactly("french", "rice", "season fruit", "pizza");
    }

    @Test
    void distinct_메서드는_고유_요소로_이루어진_스트림을_반환한다() {
        final List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4, 4);
        final List<Integer> actual = numbers.stream()
                // 짝수인 '고유' 요소만 필터링
                .filter(i -> i % 2 == 0)
                .distinct()
                .collect(Collectors.toList());

        assertThat(actual).hasSize(2)
                .containsExactly(2, 4);
    }
}
