package de.fhdo.wegistweg.backend.repository;


import de.fhdo.wegistweg.backend.domain.ProductInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInteractionRepository extends JpaRepository<ProductInteraction, Long> {
}
