package de.fhdo.wegistweg.backend.converters;

import de.fhdo.wegistweg.backend.domain.Product;
import de.fhdo.wegistweg.backend.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto entityToDto(Product entity) {
        ProductDto dto = new ProductDto();
        dto.setId(entity.getId());
        dto.setStock(entity.getStock());
        dto.setPrice(entity.getPrice());

        return dto;
    }
}
