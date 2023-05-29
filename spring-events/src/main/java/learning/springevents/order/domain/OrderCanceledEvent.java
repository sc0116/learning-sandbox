package learning.springevents.order.domain;

public class OrderCanceledEvent {

    private final Long orderNumber;

    public OrderCanceledEvent(final Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }
}
