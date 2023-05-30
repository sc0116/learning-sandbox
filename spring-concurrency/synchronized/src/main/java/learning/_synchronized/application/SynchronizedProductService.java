package learning._synchronized.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SynchronizedProductService {

    private final ProductService productService;

    public synchronized void purchase(final Long id, final Long quantity) {
        productService.purchase(id, quantity);
    }
}
