package de.fhdo.wegistweg.backend.services;

import de.fhdo.wegistweg.backend.domain.Product;

public interface ProductService {
    int getCurrentStock(Product product);
}
