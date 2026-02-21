package com.yassine.bookshop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemDto {

    private Long userId;

    private Long bookId;

    private Integer quantity;

    private BigDecimal unitPrice;
}
