package com.akkodis.xsoto.hexagonal.infraestructure.adapter.repository;

import com.akkodis.xsoto.hexagonal.infraestructure.adapter.entity.PricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PricesRepository extends JpaRepository<PricesEntity, Long> {
    List<PricesEntity> findByIdIn(List<Long> tasksIds);

    @Query("SELECT u FROM PricesEntity u WHERE CAST(:applicationDate AS timestamp) BETWEEN u.startDate AND u.endDate AND u.productId = :productId AND u.brandId = :brandId  ORDER BY u.priority DESC")
    List<PricesEntity> getByApplicationDate(@Param("applicationDate") LocalDateTime applicationDate,
                                            @Param("productId") Long productId,
                                            @Param("brandId") Long brandId,
                                            Pageable pageable);
}
