package learning._optimisticlock.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long quantity;

    @Version
    private Long version;

    public Product(final String name, final Long quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public void decrease(final Long quantity) {
        if (getQuantity() < quantity) {
            throw new RuntimeException("수량이 부족합니다.");
        }
        this.quantity -= quantity;
    }
}
