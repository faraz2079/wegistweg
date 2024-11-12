package de.fhdo.wegistweg.backend.repository;


import de.fhdo.wegistweg.backend.domain.ProductInteraction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInteractionRepository extends JpaRepository<ProductInteraction, Long> {

    /**
     * @param pageable tp limit the results
     * @return List of Object[] where Object[0] is the Product and Object[1] is the view count as a long.
     */
    @Query(value = """
    SELECT pi.product, COUNT(pi) AS viewCount
    FROM ProductInteraction pi
    WHERE pi.interactionType = de.fhdo.wegistweg.backend.domain.ProductInteractionType.VIEW_START
    GROUP BY pi.product
    HAVING COUNT(pi) > 0
    ORDER BY viewCount DESC
    """)
    List<Object[]> findMostViewedProducts(Pageable pageable);
}
