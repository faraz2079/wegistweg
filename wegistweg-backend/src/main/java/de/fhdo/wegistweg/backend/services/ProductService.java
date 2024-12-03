package de.fhdo.wegistweg.backend.services;

import de.fhdo.wegistweg.backend.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    int getCurrentStock(Product product);
}
