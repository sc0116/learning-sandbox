package learning.springtransaction.propagation.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RequiresNewTest extends ApplicationTest {

    @DisplayName("정상적인 경우 부모, 자식 모두 커밋된다")
    @Order(1)
    @Test
    void testRequiresNew() {
        sut.testRequiresNew();

        findAll();
    }

    @DisplayName("자식 트랜잭션에서 예외가 발생하면 부모, 자식 모두 롤백된다")
    @Order(2)
    @Test
    void testRequiresNew_childException() {
        assertThatThrownBy(() -> sut.testRequiresNew_ChildException())
                .isInstanceOf(RuntimeException.class);

        findAll();
    }

    @DisplayName("자식 트랜잭션에서 예외가 발생했지만 부모에서 try-catch로 잡으면 부모만 커밋된다")
    @Order(3)
    @Test
    void testRequiresNew_ChildException_ParentTryCatch() {
        sut.testRequiresNew_ChildException_ParentTryCatch();

        findAll();
    }

    @DisplayName("자식 트랜잭션에서 예외가 발생했지만 자식에서 try-catch로 잡으면 부모, 자식 모두 커밋된다")
    @Order(4)
    @Test
    void testRequiresNews_ChildException_ChildTryCatch() {
        sut.testRequiresNew_ChildException_ChildTryCatch();

        findAll();
    }

    @DisplayName("부모 트랜잭션에서 예외가 발생하면 자식만 커밋된다")
    @Order(5)
    @Test
    void testRequiresNew_parentException() {
        assertThatThrownBy(() -> sut.testRequiresNew_ParentException())
                .isInstanceOf(RuntimeException.class);

        findAll();
    }
}