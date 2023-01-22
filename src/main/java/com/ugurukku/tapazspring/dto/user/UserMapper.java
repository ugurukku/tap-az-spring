package com.ugurukku.tapazspring.dto.user;

import com.ugurukku.tapazspring.entities.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.FIELD)
public interface UserMapper {

    User toUser(CreateUserRequest from);

    UserDto toUserDto(User from);

    List<UserDto> toUserDtoList(List<User> from);

}
