package de.fhdo.wegistweg.service;

import de.fhdo.wegistweg.entity.Product;
import de.fhdo.wegistweg.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public int getCurrentStock(Product product) {
        return productRepository.findById(product.getId())
                .orElseThrow()
                .getStock();
    }
}
