package de.fhdo.wegistweg.api.graphql;

import de.fhdo.wegistweg.dto.ProductViewCountDto;
import de.fhdo.wegistweg.service.ProductInteractionService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import java.util.List;

import java.time.Duration;


@Controller
public class ProductInteractionGraphQlController {
    private final ProductInteractionService productInteractionService;


    public ProductInteractionGraphQlController(ProductInteractionService productInteractionService) {
        this.productInteractionService = productInteractionService;
    }

    @QueryMapping("productsMostViewed")
    public List<ProductViewCountDto> getMostViewedProducts() {
        return productInteractionService.getTopTenMostViewedProducts_today();
    }

    @MutationMapping("startViewing")
    public boolean startViewing(@Argument long productId,@Argument long userId) {
        productInteractionService.startViewing(productId, userId);
        return true;
    }

    @MutationMapping("stopViewing")
    public boolean stopViewing(@Argument long productId,@Argument long userId) {
        productInteractionService.stopViewing(productId, userId);
        return true;
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
