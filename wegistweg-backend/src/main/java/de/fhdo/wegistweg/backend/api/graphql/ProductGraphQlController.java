package de.fhdo.wegistweg.backend.api.graphql;

import de.fhdo.wegistweg.backend.domain.Product;
import de.fhdo.wegistweg.backend.services.ProductService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductGraphQlController {

    private final ProductService productService;

    public ProductGraphQlController(ProductService productService) {
        this.productService = productService;
    }

    @QueryMapping("products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }
}
