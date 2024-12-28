package de.fhdo.wegistweg.api.thymeleaf;

import de.fhdo.wegistweg.dto.ProductViewCountDto;
import de.fhdo.wegistweg.service.ProductInteractionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("th/products")
public class ThymeleafProductController {

    private final ProductInteractionService productInteractionService;

    public ThymeleafProductController(ProductInteractionService productInteractionService) {
        this.productInteractionService = productInteractionService;
    }

    @GetMapping("/mostViewed")
    public String showMostViewed(Model model) {
        List<ProductViewCountDto> mostViewedProducts = productInteractionService.getTopTenMostViewedProducts_today();

        model.addAttribute("products", mostViewedProducts);
        return "products_mostViewed";
    }
}
