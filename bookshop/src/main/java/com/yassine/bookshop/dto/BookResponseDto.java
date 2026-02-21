package com.yassine.bookshop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private BigDecimal price;
    private Integer stock;
    private String description;
    private Long categoryId;
}
