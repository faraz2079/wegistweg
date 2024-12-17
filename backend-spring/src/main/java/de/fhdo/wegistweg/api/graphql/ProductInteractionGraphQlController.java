package de.fhdo.wegistweg.api.graphql;

import de.fhdo.wegistweg.service.ProductInteractionService;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class ProductInteractionGraphQlController {
    private final ProductInteractionService productInteractionService;


    public ProductInteractionGraphQlController(ProductInteractionService productInteractionService) {
        this.productInteractionService = productInteractionService;
    }

    public Flux<Integer> getProductViews(Long productId) {
        return null;
    }

}
