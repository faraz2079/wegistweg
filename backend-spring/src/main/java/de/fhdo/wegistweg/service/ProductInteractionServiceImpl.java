package de.fhdo.wegistweg.service;

import de.fhdo.wegistweg.converters.ProductInteractionMapper;
import de.fhdo.wegistweg.converters.ProductMapper;
import de.fhdo.wegistweg.entity.*;
import de.fhdo.wegistweg.dto.ProductDto;
import de.fhdo.wegistweg.dto.ProductViewCountDto;
import de.fhdo.wegistweg.repository.ProductInteractionRepository;
import de.fhdo.wegistweg.repository.ProductRepository;
import de.fhdo.wegistweg.repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

@Service
public class ProductInteractionServiceImpl implements ProductInteractionService{
    /** ProductId -> Set<ActorId> */
    private final Map<Long, Set<String>> activeViewersByProduct = new ConcurrentHashMap<>();
    private final Sinks.Many<Long> activeViewersByProductSink = Sinks.many().multicast().directBestEffort();

    private final ProductRepository productRepository;
    private final ProductInteractionRepository productInteractionRepository;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    public ProductInteractionServiceImpl(ProductRepository productRepository, ProductInteractionRepository productInteractionRepository, UserRepository userRepository, ProductInteractionMapper productInteractionMapper, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productInteractionRepository = productInteractionRepository;
        this.userRepository = userRepository;
        this.productMapper = productMapper;
    }

    @Override
    public void startViewing(long productId, Long userId, String guestSessionId) {
        ProductInteraction productInteraction;
        Product product = productRepository.findById(productId).orElseThrow();

        if (userId != null) {
            User user = userRepository.findById(userId).orElseThrow();
            productInteraction = new RegisteredUserProductInteraction(product, LocalDateTime.now(), ProductInteractionType.VIEW_START, user);
        } else {
            productInteraction = new GuestProductInteraction(product, LocalDateTime.now(), ProductInteractionType.VIEW_START, guestSessionId);
        }

        addProductInteraction(productInteraction);
    }

    @Override
    public void stopViewing(long productId, Long userId, String guestSessionId) {
        ProductInteraction productInteraction;
        Product product = productRepository.findById(productId).orElseThrow();

        if (userId != null) {
            User user = userRepository.findById(userId).orElseThrow();
            productInteraction = new RegisteredUserProductInteraction(product, LocalDateTime.now(), ProductInteractionType.VIEW_END, user);
        } else {
            productInteraction = new GuestProductInteraction(product, LocalDateTime.now(), ProductInteractionType.VIEW_END, guestSessionId);
        }

        addProductInteraction(productInteraction);
    }

    @Override
    public void addProductInteraction(ProductInteraction productInteraction) {
        final Long productId = productInteraction.getProduct().getId();
        final String actorId = productInteraction.getActorIdentifier();

        switch (productInteraction.getInteractionType()) {
            case VIEW_START -> processStartViewingInteraction(productId, actorId);
            case VIEW_END -> processStopViewingInteraction(productId, actorId);
        }

        productInteractionRepository.save(productInteraction);
    }

    @Override
    public Flux<Integer> subscribeToPageViews(Long productId) {
        return activeViewersByProductSink.asFlux()
                .filter(updatedProductId -> updatedProductId.equals(productId))
                .map(this::getCurrentPageViews)
                .startWith(this.getCurrentPageViews(productId));
    }

    @Override
    public int getCurrentPageViews(Long productId) {
        // TODO Stattdessen Datenbank nutzen?
        return activeViewersByProduct.getOrDefault(productId, Collections.emptySet()).size();
    }

    @Override
    public List<ProductViewCountDto> getTopTenMostViewedProducts_allTime() {
        Pageable topTen = Pageable.ofSize(10);
        List<Object[]> results = productInteractionRepository.findMostViewedProducts(topTen);

        return convertResults(results);
    }


    @Override
    public List<ProductViewCountDto> getTopTenMostViewedProducts_today() {
        Pageable topTen = Pageable.ofSize(10);
        LocalDateTime startOfToday = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        List<Object[]> results = productInteractionRepository.findMostViewedProducts(topTen, startOfToday);

        return convertResults(results);
    }

    /**
     *
     * @param results List of Object[] where Object[0] is of type Product and Object[1] is the view count as a long.
     * @return ProductViewCountDto representation of the param.
     */
    private List<ProductViewCountDto> convertResults(List<Object[]> results) {
        return results.stream()
                .map(result -> {
                    final Product product = (Product) result[0];
                    final ProductDto productDto = productMapper.entityToDto(product);
                    final long viewCount = (long) result[1];

                    return new ProductViewCountDto(productDto,viewCount);
                })
                .collect(Collectors.toList());
    }

    private void processStartViewingInteraction(Long productId, String actorId) {
        final Set<String> viewers = activeViewersByProduct.get(productId);

        if (viewers != null) {
            viewers.add(actorId);
        }
        else {
            Set<String> newViewers = new ConcurrentSkipListSet<>();
            newViewers.add(actorId);
            activeViewersByProduct.put(productId, newViewers);
        }

        activeViewersByProductSink.tryEmitNext(productId);
    }
    private void processStopViewingInteraction(Long productId, String actorId) {
        final Set<String> viewers = activeViewersByProduct.get(productId);

        if (viewers != null) {
            viewers.remove(actorId);
            activeViewersByProductSink.tryEmitNext(productId);
        }
    }
}
