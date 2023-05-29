package learning.springevents.order.application;

import static org.assertj.core.api.Assertions.assertThat;

import learning.springevents.order.domain.Order;
import learning.springevents.order.domain.OrderCanceledEvent;
import learning.springevents.order.domain.OrderRepository;
import learning.springevents.order.domain.OrderState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;

@RecordApplicationEvents
@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService sut;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ApplicationEvents applicationEvents;

    @Test
    void cancelOrder() {
        final Order order = createOrder();

        sut.cancel(order.getId());

        final long actual = applicationEvents.stream(OrderCanceledEvent.class).count();
        assertThat(actual).isOne();
    }

    private Order createOrder() {
        return orderRepository.save(new Order(OrderState.PAYMENT_WAITING));
    }
}