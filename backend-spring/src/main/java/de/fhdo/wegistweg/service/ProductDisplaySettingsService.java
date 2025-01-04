package de.fhdo.wegistweg.service;

import de.fhdo.wegistweg.dto.ProductDisplaySettingsDto;
import reactor.core.publisher.Flux;

public interface ProductDisplaySettingsService {
    Flux<ProductDisplaySettingsDto> subscribeToDisplaySettingsUpdates(Long productId);
    ProductDisplaySettingsDto getDisplaySettings(Long productId);
    void updateDisplaySettings(ProductDisplaySettingsDto settings);
}
