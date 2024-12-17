package de.fhdo.wegistweg.Repository;

import de.fhdo.wegistweg.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
