package de.fhdo.wegistweg.api.rest;

import de.fhdo.wegistweg.dto.ProductDisplaySettingsDto;
import de.fhdo.wegistweg.dto.ProductDto;
import de.fhdo.wegistweg.service.ProductDisplaySettingsService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productDisplaySettings")
public class ProductDisplaySettingsController {
    private final ProductDisplaySettingsService service;

    public ProductDisplaySettingsController(ProductDisplaySettingsService service) {
        this.service = service;
    }

    @PostMapping
    public void updateProductDisplaySettings(@RequestBody ProductDisplaySettingsDto settingsDto) {
        service.updateDisplaySettings(settingsDto);
    }

    @GetMapping("/{id}")
    public ProductDisplaySettingsDto getDisplaySettings(@PathVariable long id) {
        return service.getDisplaySettings(id);
    }
}
