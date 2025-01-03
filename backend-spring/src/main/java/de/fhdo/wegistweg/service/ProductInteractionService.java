package de.fhdo.wegistweg.service;

import de.fhdo.wegistweg.entity.ProductInteraction;
import de.fhdo.wegistweg.dto.ProductInteractionDto;
import de.fhdo.wegistweg.dto.ProductViewCountDto;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ProductInteractionService {
    void startViewing(long productId, Long userId, String guestSessionId);
    void stopViewing(long productId, Long userId, String guestSessionId);
    void addProductInteraction(ProductInteraction productInteraction);

    Flux<Integer> subscribeToPageViews(Long productId);

    // TODO Im Interface auch Product oder ProductDTO anbieten?
    int getCurrentPageViews(Long productId);

    List<ProductViewCountDto> getTopTenMostViewedProducts_allTime();
    List<ProductViewCountDto> getTopTenMostViewedProducts_today();

}
