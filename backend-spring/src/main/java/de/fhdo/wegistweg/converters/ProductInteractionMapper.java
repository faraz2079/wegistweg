package de.fhdo.wegistweg.converters;

import de.fhdo.wegistweg.entity.ProductInteraction;
import de.fhdo.wegistweg.dto.ProductInteractionDto;
import de.fhdo.wegistweg.repository.ProductRepository;
import de.fhdo.wegistweg.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductInteractionMapper {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductInteractionMapper(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public ProductInteractionDto entityToDto(ProductInteraction entity) {
        return new ProductInteractionDto(
                entity.getProduct().getId(),
                entity.getUser().getId(),
                entity.getInteractionType(),
                entity.getTimestamp() );
    }

    public ProductInteraction dtoToEntity(ProductInteractionDto dto) {
        return new ProductInteraction(
                productRepository.findById(dto.getProductId()).orElseThrow(),
                userRepository.findById(dto.getUserId()).orElseThrow(),
                dto.getTimestamp(),
                dto.getInteractionType());
    }
}
