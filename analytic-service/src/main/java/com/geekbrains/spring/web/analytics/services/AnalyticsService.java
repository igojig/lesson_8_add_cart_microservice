package com.geekbrains.spring.web.analytics.services;

import com.geekbrains.spring.web.analytics.entities.ProductInfo;
import com.geekbrains.spring.web.analytics.integration.ProductServiceIntegration;
import com.geekbrains.spring.web.analytics.repositories.AnalyticsRepository;
import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.api.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsService {
    private final AnalyticsRepository analyticsRepository;
    private final ProductServiceIntegration productServiceIntegration;

    @Value("${utils.product.prefix}")
    private String prefix;

    @Transactional
    public void addToAnalytics(Long productId) {

        if (analyticsRepository.existsByProductId(productId)) {
            ProductInfo productInfo = analyticsRepository.getByProductId(productId)
                    .orElseThrow(()->new ResourceNotFoundException("Продукт не найден. id=" +productId));
            productInfo.setAddedAt(LocalDateTime.now());
        } else {
            ProductInfo productInfo = new ProductInfo();
            productInfo.setProductId(productId);
            productInfo.setAddedAt(LocalDateTime.now());
            analyticsRepository.save(productInfo);
        }
    }

    public List<ProductDto> getLastFiveProducts() {
//         List<ProductInfo> productInfos=analyticsRepository.getLastFiveProducts();
         List<ProductInfo> productInfos=analyticsRepository.findTop5AllByOrderByAddedAtDesc();

        return productInfos.stream()
                 .map(p->productServiceIntegration
                         .findById(p.getProductId())
                         .orElseThrow(()->new ResourceNotFoundException("Продукт не найден. id=" + p.getProductId())))
                 .toList();

    }

    public void clear() {
        analyticsRepository.deleteAll();
    }
}
