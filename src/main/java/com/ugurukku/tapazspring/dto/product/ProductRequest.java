package com.ugurukku.tapazspring.dto.product;


import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public record ProductRequest(
        String title,

        BigDecimal price,

        String description,

        String image,

        CategoryRequest category,

        CityRequest city,

        MultipartFile file) {


}
