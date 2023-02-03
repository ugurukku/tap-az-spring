package com.ugurukku.tapazspring.dto.product;

import com.ugurukku.tapazspring.dto.product.category.CategoryDto;
import com.ugurukku.tapazspring.dto.product.city.CityDto;
import com.ugurukku.tapazspring.exceptions.user.entities.Category;
import com.ugurukku.tapazspring.exceptions.user.entities.City;
import com.ugurukku.tapazspring.exceptions.user.entities.Product;
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
