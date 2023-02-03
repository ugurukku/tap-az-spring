package com.ugurukku.tapazspring.dto.product;

import com.ugurukku.tapazspring.dto.product.city.CityDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProductAllResponse(
        Long id,

         String title,

         BigDecimal price,

         LocalDate date,

         CityDto city   ) {



}
