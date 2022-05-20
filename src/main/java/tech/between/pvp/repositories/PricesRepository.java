package tech.between.pvp.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.between.pvp.entities.PricesEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PricesRepository extends JpaRepository<PricesEntity, Integer> {
    @Query("select p from PricesEntity p where p.productId = :productId " +
            "AND p.brandId = :brandId AND " +
            ":fecha BETWEEN p.startDate AND p.endDate " +
            "ORDER BY p.priority DESC")
    List<PricesEntity> findAlgo(
            @Param("productId") Long productId,
            @Param("brandId") Integer brandId,
            @Param("fecha") LocalDateTime fecha,
            Pageable pageable
    );
}
