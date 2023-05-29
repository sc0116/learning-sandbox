package learning.springevents.order.infrastructure;

import learning.springevents.order.application.RefundService;
import learning.springevents.order.domain.OrderCanceledEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderCanceledEventHandler {

    private final RefundService refundService;

    @EventListener(OrderCanceledEvent.class)
    public void handle(final OrderCanceledEvent event) {
        refundService.refund(event.getOrderNumber());
    }
}
