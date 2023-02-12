package com.ugurukku.tapazspring.dto.product;


import com.ugurukku.tapazspring.dto.product.category.CategoryRequest;
import com.ugurukku.tapazspring.dto.product.city.CityRequest;

import java.math.BigDecimal;

public record ProductRequest(String title,
                             BigDecimal price,
                             String description,
                             CityRequest city,
                             CategoryRequest category) {


}
