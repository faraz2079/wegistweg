package de.fhdo.wegistweg.repository;


import de.fhdo.wegistweg.entity.ProductInteraction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductInteractionRepository extends JpaRepository<ProductInteraction, Long> {

    /**
     * @param pageable to limit the results
     * @return List of Object[] where Object[0] is the Product and Object[1] is the view count as a long.
     */
    @Query(value = """
    SELECT pi.product, COUNT(pi) AS viewCount
    FROM ProductInteraction pi
    WHERE pi.interactionType = de.fhdo.wegistweg.entity.ProductInteractionType.VIEW_START
    GROUP BY pi.product
    HAVING COUNT(pi) > 0
    ORDER BY viewCount DESC
    """)
    List<Object[]> findMostViewedProducts(Pageable pageable);

    /**
     * @param pageable to limit the results
     * @param since only display results between "since" and now.
     * @return List of Object[] where Object[0] is the Product and Object[1] is the view count as a long.
     */
    @Query(value = """
    SELECT pi.product, COUNT(pi) AS viewCount
    FROM ProductInteraction pi
    WHERE pi.interactionType = de.fhdo.wegistweg.entity.ProductInteractionType.VIEW_START
        AND pi.timestamp >= :since
    GROUP BY pi.product
    HAVING COUNT(pi) > 0
    ORDER BY viewCount DESC
    """)
    List<Object[]> findMostViewedProducts(Pageable pageable, @Param("since") LocalDateTime since);
}
