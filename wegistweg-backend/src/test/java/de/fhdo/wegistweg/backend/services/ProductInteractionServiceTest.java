package de.fhdo.wegistweg.backend.services;

import de.fhdo.wegistweg.backend.domain.Product;
import de.fhdo.wegistweg.backend.dto.ProductViewCountDto;
import de.fhdo.wegistweg.backend.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootTest
class ProductInteractionServiceTest {

    private final ProductInteractionService service;
    private final ProductRepository productRepository;

    @Autowired
    ProductInteractionServiceTest(ProductInteractionService service, ProductRepository productRepository) {
        this.service = service;
        this.productRepository = productRepository;
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
        List<ProductViewCountDto> results = service.getTopTenMostViewedProducts();
        System.out.println("Size: " + results.size());
        System.out.println("Results: " + results);
    }
}