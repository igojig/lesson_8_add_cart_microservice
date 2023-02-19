package com.geekbrains.spring.web.analytics.controllers;

import com.geekbrains.spring.web.analytics.entities.ProductInfo;
import com.geekbrains.spring.web.analytics.services.AnalyticsService;
import com.geekbrains.spring.web.api.core.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @PostMapping("/addToTopProducts")
    public void addToTop(@RequestParam(name = "productId") Long productId){
        analyticsService.addToAnalytics(productId);
    }

    @GetMapping("/getTopFiveProducts")
    public List<ProductDto> getTopFiveProducts(){
        return analyticsService.getLastFiveProducts();
    }

    @DeleteMapping
    public void clearTopFiveProducts(){
        analyticsService.clear();
    }

}
