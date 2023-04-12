package basic;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SlicingTest {

    private List<Dish> specialMenu;

    @BeforeEach
    void setUp() {
        specialMenu = Arrays.asList(
                new Dish("season fruit", true, 120, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french", true, 530, Type.OTHER)
        );
    }

    @Test
    void 이미_정렬되어_있는_경우_takeWhile_메서드는_프레디케이트가_거짓일_때_반복_장업을_중단한다() {
        /*
        아래 코드는 전체(5번) 반복함
        final List<basic.Dish> filteredMenu = specialMenu.stream()
                .filter(dish -> dish.getCalories() < 320)
                .collect(Collectors.toList());
         */

        // 아래 코드는 3번만 반복함
        final List<String> actual = specialMenu.stream()
                .takeWhile(dish -> dish.getCalories() < 320)
                .map(Dish::toString)
                .collect(Collectors.toList());

        assertThat(actual).hasSize(2)
                .containsExactly("season fruit", "prawns");
    }

    @Test
    void 이미_정렬되어_있는_경우_dropWhile_메서드는_프레디케이트가_거짓일_때_그_지점에서_작업을_중단하고_남은_모든_요소를_반환한다() {
        // 아래 코드는 3번만 반복함
        final List<String> actual = specialMenu.stream()
                .dropWhile(dish -> dish.getCalories() < 320)
                .map(Dish::toString)
                .collect(Collectors.toList());

        assertThat(actual).hasSize(3)
                .containsExactly("rice", "chicken", "french");
    }

    @Test
    void limit_메서드는_주어진_값_이하의_크기를_갖는_새로운_스트림을_반환한다() {
        // 100 칼로리 이상인 음식은 총 5개지만 3개만 반환함
        final List<String> actual = specialMenu.stream()
                .filter(dish -> dish.getCalories() > 100)
                .limit(3)
                .map(Dish::toString)
                .collect(Collectors.toList());

        assertThat(actual).hasSize(3)
                .containsExactly("season fruit", "prawns", "rice");
    }

    @Test
    void skip_메서드는_처음_n개의_요소를_제외한_스트림을_반환한다() {
        final List<String> actual = specialMenu.stream()
                .filter(dish -> dish.getCalories() > 100)
                .skip(2)
                .map(Dish::toString)
                .collect(Collectors.toList());

        assertThat(actual).hasSize(3)
                .containsExactly("rice", "chicken", "french");
    }
}
