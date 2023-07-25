import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class OrElseAndOrElseGetTest {

    @DisplayName("orElse()는 null 여부와 관계없이 항상 동작한다.")
    @Nested
    class OrElseTest {

        @DisplayName("orElse()의 값이 null 값이 아닌 경우")
        @Test
        void testOrElse_notNull() {
            final StringBuilder sb = new StringBuilder("짱구");
            final StringBuilder actual = Optional.of(sb).orElse(testMethod(sb));

            assertThat(actual.toString()).isEqualTo("짱구 흰둥이");
        }

        @DisplayName("orElse()의 값이 null 값인 경우")
        @Test
        void testOrElse_null() {
            final StringBuilder sb = new StringBuilder("짱구");
            final Object actual = Optional.ofNullable(null).orElse(testMethod(sb));

            assertThat(String.valueOf(actual)).isEqualTo("짱구 흰둥이");
        }
    }

    @DisplayName("orElseGet()은 null 일 때만 동작한다.")
    @Nested
    class OrElseGetTest {

        @DisplayName("orElseGet()의 값이 null 값이 아닌 경우")
        @Test
        void testOrElseGet_notNull() {
            final StringBuilder sb = new StringBuilder("짱구");
            final StringBuilder actual = Optional.of(sb).orElseGet(() -> testMethod(sb));

            assertThat(actual.toString()).isEqualTo("짱구");
        }

        @DisplayName("orElseGet()의 값이 null 값인 경우")
        @Test
        void testOrElseGet_null() {
            final StringBuilder sb = new StringBuilder("짱구");
            final Object actual = Optional.ofNullable(null).orElseGet(() -> testMethod(sb));

            assertThat(String.valueOf(actual)).isEqualTo("짱구 흰둥이");
        }
    }

    private StringBuilder testMethod(final StringBuilder sb) {
        System.out.println("메서드 실행 여부 확인 용도 출력문");
        return sb.append(" 흰둥이");
    }
}
