package com.yassine.bookshop.dto;

import com.yassine.bookshop.entities.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class CartResponse {

    private List<ItemDto> itemsDto;
    private BigDecimal total;
    private Long userId;

}