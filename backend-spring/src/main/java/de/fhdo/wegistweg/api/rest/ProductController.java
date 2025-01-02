package de.fhdo.wegistweg.api.rest;

import de.fhdo.wegistweg.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import de.fhdo.wegistweg.entity.Product;
import de.fhdo.wegistweg.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getProducts();
    }
}
