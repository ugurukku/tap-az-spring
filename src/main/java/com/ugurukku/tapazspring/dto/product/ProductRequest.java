package com.ugurukku.tapazspring.dto.product;


import java.math.BigDecimal;

public record ProductRequest(String title,
                             BigDecimal price,
                             String description,
                             CityRequest city,
                             CategoryRequest category) {


}
