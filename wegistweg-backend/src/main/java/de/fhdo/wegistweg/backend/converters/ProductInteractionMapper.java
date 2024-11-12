package de.fhdo.wegistweg.backend.converters;

import de.fhdo.wegistweg.backend.domain.ProductInteraction;
import de.fhdo.wegistweg.backend.dto.ProductInteractionDto;
import de.fhdo.wegistweg.backend.repository.ProductRepository;
import de.fhdo.wegistweg.backend.repository.UserRepository;
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
