package com.geekbrains.spring.web.analytics.repositories;

import com.geekbrains.spring.web.analytics.entities.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnalyticsRepository extends JpaRepository<ProductInfo, Long> {
//    @Query(value = "Select * from products_info order by added_at DESC limit 5", nativeQuery = true)
//    public List<ProductInfo> getLastFiveProducts();

    public List<ProductInfo> findTop5AllByOrderByAddedAtDesc();

    public boolean existsByProductId(Long productId);
    public Optional<ProductInfo> getByProductId(Long productId);
}
