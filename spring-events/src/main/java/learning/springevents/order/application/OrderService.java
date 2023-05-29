package learning.springevents.order.application;

import learning.springevents.order.domain.Order;
import learning.springevents.order.domain.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public void cancel(final Long id) {
        System.out.println("OrderService 시작!");

        final Order order = orderRepository.findById(id);
        order.cancel();

        System.out.println("OrderService 종료!");
    }
}
