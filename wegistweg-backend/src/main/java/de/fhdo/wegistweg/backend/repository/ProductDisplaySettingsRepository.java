package de.fhdo.wegistweg.backend.repository;


import de.fhdo.wegistweg.backend.domain.ProductDisplaySettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDisplaySettingsRepository extends JpaRepository<ProductDisplaySettings, Long> {
}
