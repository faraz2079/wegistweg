package de.fhdo.wegistweg.service;

import de.fhdo.wegistweg.dto.ProductDto;
import de.fhdo.wegistweg.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> getProducts();
    ProductDto getProduct(long productId);
    int getCurrentStock(long productId);
}
