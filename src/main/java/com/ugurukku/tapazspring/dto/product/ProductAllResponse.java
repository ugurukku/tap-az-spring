package com.ugurukku.tapazspring.dto.product;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProductAllResponse(
        Long id,

         String title,

         BigDecimal price,

         String description,

         String image,

         LocalDate date,

         CityDto city,

         CategoryDto category) {



}
