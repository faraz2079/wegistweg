package de.fhdo.wegistweg.service;

import de.fhdo.wegistweg.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    int getCurrentStock(Product product);
}
