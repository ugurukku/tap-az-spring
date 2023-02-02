package com.ugurukku.tapazspring.dto.product;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public record ProductRequest(

        String title,

        BigDecimal price,

        String description,

        CityRequest city,

        CategoryRequest category,


       @Nullable @JsonIgnore MultipartFile image) {


}
