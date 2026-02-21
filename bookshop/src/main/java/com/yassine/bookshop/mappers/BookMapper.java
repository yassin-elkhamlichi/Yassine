package com.yassine.bookshop.mappers;


import com.yassine.bookshop.dto.BookResponseDto;
import com.yassine.bookshop.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "categoryId", source = "category.id")
    BookResponseDto toResponseDto(Book book);
}
