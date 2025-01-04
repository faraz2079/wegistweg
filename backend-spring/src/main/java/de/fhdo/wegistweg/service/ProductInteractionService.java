package de.fhdo.wegistweg.service;

import de.fhdo.wegistweg.entity.ProductInteraction;
import de.fhdo.wegistweg.dto.ProductInteractionDto;
import de.fhdo.wegistweg.dto.ProductViewCountDto;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ProductInteractionService {
    // TODO Wie gestalten? Methoden für DTO UND Entity anbieten?
    void addProductInteraction(ProductInteraction productInteraction);
    void addProductInteraction(ProductInteractionDto productInteractionDto);

    Flux<Integer> subscribeToPageViews(Long productId);

    // TODO Im Interface auch Product oder ProductDTO anbieten?
    int getCurrentPageViews(Long productId);

    List<ProductViewCountDto> getTopTenMostViewedProducts_allTime();
    List<ProductViewCountDto> getTopTenMostViewedProducts_today();

}
