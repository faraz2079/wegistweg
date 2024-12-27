package de.fhdo.wegistweg.converters;

import de.fhdo.wegistweg.entity.Product;
import de.fhdo.wegistweg.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto entityToDto(Product entity) {
        ProductDto dto = new ProductDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setStock(entity.getStock());
        dto.setPrice(entity.getPrice());

        return dto;
    }
}
