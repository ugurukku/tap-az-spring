package com.ugurukku.tapazspring.dto;

import com.ugurukku.tapazspring.entities.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.FIELD)
public interface UserMapper {


    User toUser(CreateUserRequest from);

}
