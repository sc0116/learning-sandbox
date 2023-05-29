package learning.springevents.order.infrastructure.paygate;

import learning.springevents.order.application.RefundService;
import org.springframework.stereotype.Component;

@Component
public class ExternalRefundService implements RefundService {

    @Override
    public void refund(final Long id) {
        System.out.printf("주문번호: %d - 환불 완료!%n", id);
    }
}
