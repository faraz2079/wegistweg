package de.fhdo.wegistweg.api.graphql;

import de.fhdo.wegistweg.service.ProductInteractionService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;


@Controller
public class ProductInteractionGraphQlController {
    private final ProductInteractionService productInteractionService;


    public ProductInteractionGraphQlController(ProductInteractionService productInteractionService) {
        this.productInteractionService = productInteractionService;
    }

    @SubscriptionMapping("productViews")
    public Flux<Integer> getProductViews(@Argument Long productId) {
        return productInteractionService.subscribeToPageViews(productId);
    }

    @SubscriptionMapping("counter")
    public Flux<Integer> counter() {
        return Flux
                .interval(Duration.ofSeconds(1))
                .map(Long::intValue);
    }
}
