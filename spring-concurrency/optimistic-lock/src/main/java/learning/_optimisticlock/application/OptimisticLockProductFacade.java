package learning._optimisticlock.application;

import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OptimisticLockProductFacade {

    private final ProductService productService;

    public void purchase(final Long id, final Long quantity) throws InterruptedException {
        while (true) {
            try {
                productService.purchase(id, quantity);
                break;
            } catch (final ObjectOptimisticLockingFailureException e) {
                Thread.sleep(50);
            }
        }
    }
}
