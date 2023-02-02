package com.ugurukku.tapazspring.dto.product;

import com.ugurukku.tapazspring.entities.Category;
import com.ugurukku.tapazspring.entities.City;
import com.ugurukku.tapazspring.entities.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.FIELD)
public interface ProductMapper {

    ProductAllResponse toProductAllResponse(Product from);

    CategoryDto ToCategoryDto(Category from);

    CityDto toCityDto(City from);

}
