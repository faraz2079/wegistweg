package de.fhdo.wegistweg.backend.services;

import de.fhdo.wegistweg.backend.domain.ProductInteraction;
import de.fhdo.wegistweg.backend.dto.ProductInteractionDto;
import de.fhdo.wegistweg.backend.dto.ProductViewCountDto;

import java.util.List;

public interface ProductInteractionService {
    // TODO Wie gestalten? Methoden für DTO UND Entity anbieten?
    void addProductInteraction(ProductInteraction productInteraction);
    void addProductInteraction(ProductInteractionDto productInteractionDto);

    // TODO Im Interface auch Product oder ProductDTO anbieten?
    int getCurrentPageViews(Long productId);

    public List<ProductViewCountDto> getTopTenMostViewedProducts();

}
