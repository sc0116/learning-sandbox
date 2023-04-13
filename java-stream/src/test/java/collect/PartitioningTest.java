package collect;

import static java.util.stream.Collectors.partitioningBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class PartitioningTest {

    private final Dish pork = new Dish("pork", false, 800, Type.MEAT);
    private final Dish beef = new Dish("beef", false, 700, Type.MEAT);
    private final Dish chicken = new Dish("chicken", false, 400, Type.MEAT);
    private final Dish french = new Dish("french", true, 530, Type.OTHER);
    private final Dish rice = new Dish("rice", true, 350, Type.OTHER);
    private final Dish pizza = new Dish("pizza", true, 550, Type.OTHER);
    private final Dish salmon = new Dish("salmon", false, 450, Type.FISH);
    private final List<Dish> menu = Arrays.asList(pork, beef, chicken, french, rice, pizza, salmon);

    @Test
    void partitioningBy_메서드는_참_아니면_거짓의_값을_갖는_두_개의_그룹으로_분류한다() {
        final Map<Boolean, List<Dish>> actual = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian));

        assertThat(actual).hasSize(2)
                .contains(
                        entry(false, List.of(pork, beef, chicken, salmon)),
                        entry(true, List.of(french, rice, pizza))
                );
    }
}
