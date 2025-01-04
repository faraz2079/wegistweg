package de.fhdo.wegistweg.repository;


import de.fhdo.wegistweg.entity.ProductDisplaySettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDisplaySettingsRepository extends JpaRepository<ProductDisplaySettings, Long> {
    ProductDisplaySettings findProductDisplaySettingsByProductId(Long productId);
}
