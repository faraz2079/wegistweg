package de.fhdo.wegistweg.service;

import de.fhdo.wegistweg.entity.Product;
import de.fhdo.wegistweg.dto.ProductViewCountDto;
import de.fhdo.wegistweg.entity.ProductInteraction;
import de.fhdo.wegistweg.entity.ProductInteractionType;
import de.fhdo.wegistweg.entity.User;
import de.fhdo.wegistweg.repository.ProductRepository;
import de.fhdo.wegistweg.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class ProductInteractionServiceTest {

    private final ProductInteractionService service;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    ProductInteractionServiceTest(ProductInteractionService service, ProductRepository productRepository, UserRepository userRepository) {
        this.service = service;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }


    @Test
    void getCurrentPageViews() {
        Pageable firstTen = Pageable.ofSize(10);
        List<Product> products = productRepository.findAll(firstTen).toList();

        System.out.println("ID CURRENT_VIEWS");

        for(Product product : products) {
            final long id = product.getId();
            final long views = service.getCurrentPageViews(id);
            System.out.println(id + " " + views);
        }
    }

    @Test
    void getTopTenMostViewedProducts() {
        List<ProductViewCountDto> results = service.getTopTenMostViewedProducts_allTime();
        System.out.println("Size: " + results.size());
        System.out.println("Results: " + results);
    }

    @Test
    void subscribeToPageViews() {
        Long productId = 1L;
        Flux<Integer> flux = service.subscribeToPageViews(productId);

        int resultService = service.getCurrentPageViews(productId);
        int resultFlux = flux.blockFirst();

        Assertions.assertEquals(resultService, resultFlux);
    }


    @Test
    void subscribeToPageViews_updates() {
        Long productId = 1L;
        Flux<Integer> flux = service.subscribeToPageViews(productId);
        Product product = productRepository.getReferenceById(productId);
        User user = userRepository.getReferenceById(1L);

        // Ensure user is not currently viewing the product, so that he can START viewing later on.
        service.addProductInteraction(new ProductInteraction(product, user, LocalDateTime.now(), ProductInteractionType.VIEW_END));
        int initialViewCount = service.getCurrentPageViews(productId);

        StepVerifier.create(flux)
                .expectNext(initialViewCount)
                .then(() -> service.addProductInteraction(new ProductInteraction(product, user, LocalDateTime.now(), ProductInteractionType.VIEW_START)))
                .expectNext(initialViewCount + 1)
                .then(() -> service.addProductInteraction(new ProductInteraction(product, user, LocalDateTime.now(), ProductInteractionType.VIEW_END)))
                .expectNext(initialViewCount)
                .thenCancel()
                .verify();
    }
}