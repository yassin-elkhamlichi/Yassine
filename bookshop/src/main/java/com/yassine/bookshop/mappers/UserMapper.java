package com.yassine.bookshop.mappers;

import com.yassine.bookshop.dto.UserRegisterDto;
import com.yassine.bookshop.dto.UserResponse;
import com.yassine.bookshop.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "passwordHash", ignore = true)
    User toEntity(UserRegisterDto userRegisterDto);
    UserResponse toDto(User user);
}
