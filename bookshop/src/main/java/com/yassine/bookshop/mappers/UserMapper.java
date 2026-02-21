package com.yassine.bookshop.mappers;

import com.yassine.bookshop.dto.UserRegisterDto;
import com.yassine.bookshop.dto.UserResponse;
import com.yassine.bookshop.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRegisterDto userRegisterDto);
    UserResponse toDto(User user);
}
