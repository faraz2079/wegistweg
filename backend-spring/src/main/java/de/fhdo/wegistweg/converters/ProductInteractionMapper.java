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

}
