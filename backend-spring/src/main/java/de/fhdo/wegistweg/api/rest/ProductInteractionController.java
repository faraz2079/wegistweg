package de.fhdo.wegistweg.api.rest;

import de.fhdo.wegistweg.dto.ProductViewCountDto;
import de.fhdo.wegistweg.service.ProductInteractionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productInteraction")
public class ProductInteractionController {
    private final ProductInteractionService service;

    public ProductInteractionController(ProductInteractionService service) {
        this.service = service;
    }

    @GetMapping("/mostViewed")
    public List<ProductViewCountDto> getAllProducts() {
        return service.getTopTenMostViewedProducts_today();
    }

    @GetMapping("/views/{id}")
    public int getCurrentPageViews(@PathVariable long id) {
        return service.getCurrentPageViews(id);
    }

    @PostMapping("/start-viewing")
    public void startViewing(
            @RequestParam long productId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String guestSessionId
    ) {
        service.startViewing(productId, userId, guestSessionId);
    }

    @PostMapping("/stop-viewing")
    public void stopViewing(
            @RequestParam long productId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String guestSessionId
    ) {
        service.stopViewing(productId, userId, guestSessionId);
    }
}
