package com.ugurukku.tapazspring.dto.product;

import com.ugurukku.tapazspring.dto.product.category.CategoryDto;
import com.ugurukku.tapazspring.dto.product.city.CityDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProductResponse(String title,

                              String description,

                              BigDecimal price,

                              LocalDate date,

                              String userEmail,

                              CityDto city,

                              CategoryDto category) {
}
