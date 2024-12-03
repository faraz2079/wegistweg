package de.fhdo.wegistweg.backend.services;

import de.fhdo.wegistweg.backend.converters.ProductInteractionMapper;
import de.fhdo.wegistweg.backend.converters.ProductMapper;
import de.fhdo.wegistweg.backend.domain.Product;
import de.fhdo.wegistweg.backend.domain.ProductInteraction;
import de.fhdo.wegistweg.backend.dto.ProductDto;
import de.fhdo.wegistweg.backend.dto.ProductInteractionDto;
import de.fhdo.wegistweg.backend.dto.ProductViewCountDto;
import de.fhdo.wegistweg.backend.repository.ProductInteractionRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

@Service
public class ProductInteractionServiceImpl implements ProductInteractionService{
    /** ProductId -> Set<UserId> */
    private final Map<Long, Set<Long>> activeViewersByProduct = new ConcurrentHashMap<>();

    private final ProductInteractionRepository repository;
    private final ProductInteractionMapper productInteractionMapper;
    private final ProductMapper productMapper;

    public ProductInteractionServiceImpl(ProductInteractionRepository repositoryl, ProductInteractionMapper productInteractionMapper, ProductMapper productMapper) {
        this.repository = repositoryl;
        this.productInteractionMapper = productInteractionMapper;
        this.productMapper = productMapper;
    }


    @Override
    public void addProductInteraction(ProductInteraction productInteraction) {
        final Long productId = productInteraction.getProduct().getId();
        final Long userId = productInteraction.getUser().getId();

        switch (productInteraction.getInteractionType()) {
            case VIEW_START -> startViewing(productId, userId);
            case VIEW_END -> stopViewing(productId, userId);
        }

        repository.save(productInteraction);
    }

    @Override
    public void addProductInteraction(ProductInteractionDto productInteractionDto) {
        addProductInteraction(productInteractionMapper.dtoToEntity(productInteractionDto));
    }

    @Override
    public int getCurrentPageViews(Long productId) {
        // TODO Stattdessen Datenbank nutzen?
        return activeViewersByProduct.getOrDefault(productId, Collections.emptySet()).size();
    }

    @Override
    public List<ProductViewCountDto> getTopTenMostViewedProducts() {
        Pageable topTen = Pageable.ofSize(10);
        List<Object[]> results = repository.findMostViewedProducts(topTen);

        return results.stream()
                .map(result -> {
                    final Product product = (Product) result[0];
                    final ProductDto productDto = productMapper.entityToDto(product);
                    final long viewCount = (long) result[1];

                    return new ProductViewCountDto(productDto,viewCount);
                })
                .collect(Collectors.toList());
    }

    private void startViewing(Long productId, Long userId) {
        final Set<Long> viewers = activeViewersByProduct.get(productId);

        if (viewers != null) {
            viewers.add(userId);
        }
        else {
            Set<Long> newViewers = new ConcurrentSkipListSet<>();
            newViewers.add(userId);
            activeViewersByProduct.put(productId, newViewers);
        }
    }
    private void stopViewing(Long productId, Long userId) {
        final Set<Long> viewers = activeViewersByProduct.get(productId);

        if (viewers != null) {
            viewers.remove(userId);
        }
    }
}