package learning._optimisticlock.application;

import java.util.NoSuchElementException;
import learning._optimisticlock.domain.Product;
import learning._optimisticlock.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void purchase(final Long id, final Long quantity) {
        final Product foundProduct = getProduct(id);
        foundProduct.decrease(quantity);
    }

    private Product getProduct(final Long id) {
        return productRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
