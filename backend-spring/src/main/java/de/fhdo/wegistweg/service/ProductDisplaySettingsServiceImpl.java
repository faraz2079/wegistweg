package de.fhdo.wegistweg.service;

import de.fhdo.wegistweg.dto.ProductDisplaySettingsDto;
import de.fhdo.wegistweg.entity.ProductDisplaySettings;
import de.fhdo.wegistweg.repository.ProductDisplaySettingsRepository;
import de.fhdo.wegistweg.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
public class ProductDisplaySettingsServiceImpl implements ProductDisplaySettingsService {
    private final Sinks.Many<Long> productDisplaySettingsUpdatedSink = Sinks.many().multicast().directBestEffort();

    private final ProductDisplaySettingsRepository productDisplaySettingsRepository;
    private final ProductRepository productRepository;

    public ProductDisplaySettingsServiceImpl(ProductDisplaySettingsRepository productDisplaySettingsRepository, ProductRepository productRepository) {
        this.productDisplaySettingsRepository = productDisplaySettingsRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Flux<ProductDisplaySettingsDto> subscribeToDisplaySettingsUpdates(Long productId) {
        return productDisplaySettingsUpdatedSink.asFlux()
                .filter(updatedProductId -> updatedProductId.equals(productId))
                .map(this::getDisplaySettings)
                .startWith(this.getDisplaySettings(productId));
    }

    @Override
    public ProductDisplaySettingsDto getDisplaySettings(Long productId) {
        ProductDisplaySettings persistedSettings = productDisplaySettingsRepository.findProductDisplaySettingsByProductId(productId);

        if (persistedSettings == null) {
            return new ProductDisplaySettingsDto(productId);
        }
        else {
            return new ProductDisplaySettingsDto(productId,
                    persistedSettings.isDisplayStockLevel(),
                    persistedSettings.isDisplayViews());
        }
    }

    @Override
    public void updateDisplaySettings(ProductDisplaySettingsDto settingsDto) {
        ProductDisplaySettings persistedSettings = productDisplaySettingsRepository.findProductDisplaySettingsByProductId(settingsDto.getProductId());

        if (persistedSettings == null) {
            ProductDisplaySettings newSettings = new ProductDisplaySettings();
            newSettings.setProduct(productRepository.findById(settingsDto.getProductId()).orElseThrow());
            newSettings.setDisplayViews(settingsDto.isDisplayViews());
            newSettings.setDisplayStockLevel(settingsDto.isDisplayStockLevel());

            productDisplaySettingsRepository.save(newSettings);
        }
        else {
            persistedSettings.setDisplayViews(settingsDto.isDisplayViews());
            persistedSettings.setDisplayStockLevel(settingsDto.isDisplayStockLevel());
            productDisplaySettingsRepository.save(persistedSettings);
        }

        productDisplaySettingsUpdatedSink.tryEmitNext(settingsDto.getProductId());
    }
}
