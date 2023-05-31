package learning._pessimisticlock.application;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import learning._pessimisticlock.domain.Product;
import learning._pessimisticlock.domain.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService sut;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void 동시에_100개의_상품을_구매한다() throws InterruptedException {
        final Product product = productRepository.save(new Product("초코비", 100L));

        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        final CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> process(product, countDownLatch));
        }
        countDownLatch.await();

        final Product actual = productRepository.findById(product.getId()).orElseThrow();
        assertThat(actual.getQuantity()).isEqualTo(0L);
    }

    private void process(final Product product, final CountDownLatch countDownLatch) {
        try {
            sut.purchase(product.getId(), 1L);
        } finally {
            countDownLatch.countDown();
        }
    }
}