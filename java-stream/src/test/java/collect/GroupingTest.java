package collect;

import static java.util.stream.Collectors.filtering;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class GroupingTest {

    private final Dish pork = new Dish("pork", false, 800, Type.MEAT);
    private final Dish beef = new Dish("beef", false, 700, Type.MEAT);
    private final Dish chicken = new Dish("chicken", false, 400, Type.MEAT);
    private final Dish french = new Dish("french", true, 530, Type.OTHER);
    private final Dish rice = new Dish("rice", true, 350, Type.OTHER);
    private final Dish pizza = new Dish("pizza", true, 550, Type.OTHER);
    private final Dish salmon = new Dish("salmon", false, 450, Type.FISH);
    private final List<Dish> menu = Arrays.asList(pork, beef, chicken, french, rice, pizza, salmon);

    @Test
    void groupingBy_메서드는_그룹화_함수가_반환하는_키와_각_키에_대응하는_스트림의_모든_항목_리스트를_값으로_갖는_맵을_반환한다() {
        final Map<Type, List<Dish>> actual = menu.stream()
                .collect(groupingBy(Dish::getType));

        assertThat(actual).hasSize(3)
                .contains(
                        entry(Type.FISH, List.of(salmon)),
                        entry(Type.MEAT, List.of(pork, beef, chicken)),
                        entry(Type.OTHER, List.of(french, rice, pizza))
                );
    }

    @Test
    void filtering_메서드는_인수로_받는_프레디케이트로_각_그룹의_요소와_필터링_된_요소를_재그룹화_한다() {
        final Map<Type, List<Dish>> actual = menu.stream()
                .collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));

        assertThat(actual).hasSize(3)
                .contains(
                        entry(Type.FISH, List.of()),
                        entry(Type.MEAT, List.of(pork, beef)),
                        entry(Type.OTHER, List.of(french, pizza))
                );
    }

    @Test
    void mapping_메서드는_매핑_함수와_각_항목에_적용한_함수를_모으는_데_사용하는_또_다른_컬렉터를_인수로_받는다() {
        final Map<Type, List<String>> actual = menu.stream()
                .collect(groupingBy(Dish::getType,
                        mapping(Dish::getName, toList())));

        assertThat(actual).hasSize(3)
                .contains(
                        entry(Type.FISH, List.of("salmon")),
                        entry(Type.MEAT, List.of("pork", "beef", "chicken")),
                        entry(Type.OTHER, List.of("french", "rice", "pizza"))
                );
    }
}
