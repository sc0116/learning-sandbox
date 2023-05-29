package learning.springevents.order.infrastructure;

import learning.springevents.order.application.RefundService;
import learning.springevents.order.domain.OrderCanceledEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Component
public class OrderCanceledEventHandler {

    private final RefundService refundService;

    @Async
    @TransactionalEventListener(
            value = OrderCanceledEvent.class,
            phase = TransactionPhase.AFTER_COMMIT
    )
    public void handle(final OrderCanceledEvent event) {
        refundService.refund(event.getOrderNumber());
    }
}
