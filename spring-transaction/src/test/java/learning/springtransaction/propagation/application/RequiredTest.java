package learning.springtransaction.propagation.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class RequiredTest extends ApplicationTest {

    @DisplayName("정상적인 경우 부모, 자식 모두 커밋된다")
    @Order(1)
    @Test
    void testRequired() {
        sut.testRequired();

        findAll();
    }

    @DisplayName("자식 트랜잭션에서 예외가 발생하면 부모, 자식 모두 롤백된다")
    @Order(2)
    @Test
    void testRequired_ChildException() {
        assertThatThrownBy(() -> sut.testRequired_ChildException())
                .isInstanceOf(RuntimeException.class);

        findAll();
    }

    @DisplayName("부모 트랜잭션에서 예외가 발생하면 부모, 자식 모두 롤백된다")
    @Order(3)
    @Test
    void testRequired_ParentException() {
        assertThatThrownBy(() -> sut.testRequired_ParentException())
                .isInstanceOf(RuntimeException.class);

        findAll();
    }
}
