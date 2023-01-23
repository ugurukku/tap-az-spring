package com.ugurukku.tapazspring.dto.product;


import com.ugurukku.tapazspring.entities.Category;
import com.ugurukku.tapazspring.entities.City;

import java.math.BigDecimal;

public record ProductRequest(String title,
                             BigDecimal price,

                             String description,

                             String image,

                             Category category,

                             City city) {



}
