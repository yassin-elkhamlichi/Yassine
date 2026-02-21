package com.yassine.bookshop.mappers;



import com.yassine.bookshop.dto.ItemDto;
import com.yassine.bookshop.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target="userId" , source="user.id")
    @Mapping(target="bookId" ,  source="book.id")
    ItemDto toDto(CartItem user);
}