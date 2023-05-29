package learning.springevents.order.domain;

import learning.springevents.common.event.Event;

public class OrderCanceledEvent extends Event {

    private final Long orderNumber;

    public OrderCanceledEvent(final Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }
}
