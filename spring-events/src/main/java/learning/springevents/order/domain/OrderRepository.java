package learning.springevents.order.domain;

import org.springframework.data.repository.Repository;

public interface OrderRepository extends Repository<Order, Long> {

    Order save(Order entity);
    Order findById(Long id);
}
