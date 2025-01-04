package de.fhdo.wegistweg.api.rest;

import de.fhdo.wegistweg.dto.ProductDto;
import org.springframework.web.bind.annotation.*;
import de.fhdo.wegistweg.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getProducts();
    }


    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable long id) {
        return productService.getProduct(id);
    }
}
