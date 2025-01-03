package de.fhdo.wegistweg.api.graphql;

import de.fhdo.wegistweg.dto.ProductDisplaySettingsDto;
import de.fhdo.wegistweg.service.ProductDisplaySettingsService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class ProductDisplaySettingsGraphQlController {
    private final ProductDisplaySettingsService service;


    public ProductDisplaySettingsGraphQlController(ProductDisplaySettingsService service) {
        this.service = service;
    }

    @MutationMapping("updateProductViewSettings")
    public Boolean updateProductDisplaySettings(ProductDisplaySettingsDto updatedSettings) {
        service.updateDisplaySettings(updatedSettings);
        return true;
    }

    @SubscriptionMapping("productDisplaySettings")
    public Flux<ProductDisplaySettingsDto> subscribeToDisplaySettingUpdates(@Argument Long productId) {
        return service.subscribeToDisplaySettingsUpdates(productId);
    }
}
