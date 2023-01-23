package com.ugurukku.tapazspring.dto.product;

import com.ugurukku.tapazspring.entities.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.FIELD)
public interface ProductMapper {

    Product toProduct(ProductRequest from);

}
