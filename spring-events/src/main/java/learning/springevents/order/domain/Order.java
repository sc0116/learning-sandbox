package learning.springevents.order.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PURCHASE_ORDER")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    public Order(final OrderState state) {
        this.state = state;
    }

    public void cancel() {
        verifyNotYetShipped();
        this.state = OrderState.CANCELED;
        System.out.println("주문 취소 완료!");
    }

    private void verifyNotYetShipped() {
        System.out.println("주문 취소 가능한지 검증 완료!");
    }
}
