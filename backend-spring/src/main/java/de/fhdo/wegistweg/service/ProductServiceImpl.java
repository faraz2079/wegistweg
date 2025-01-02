package de.fhdo.wegistweg.service;

import de.fhdo.wegistweg.converters.ProductMapper;
import de.fhdo.wegistweg.dto.ProductDto;
import de.fhdo.wegistweg.entity.Product;
import de.fhdo.wegistweg.repository.ProductRepository;
import org.hibernate.query.SortDirection;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDto> getProducts() {
        return productMapper.entityToDto(productRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
    }

    @Override
    public ProductDto getProduct(long productId) {
        return productMapper.entityToDto(productRepository.findById(productId).orElseThrow());
    }

    @Override
    public int getCurrentStock(long productId) {
        return productRepository.findById(productId)
                .orElseThrow()
                .getStock();
    }
}
