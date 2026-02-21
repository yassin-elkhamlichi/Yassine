package com.yassine.bookshop.controllers;

import com.yassine.bookshop.dto.BookResponseDto;
import com.yassine.bookshop.dto.CategoryResponseDto;
import com.yassine.bookshop.services.PublicService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
@AllArgsConstructor
public class PublicController {

    private final PublicService publicService;

    // GET /api/public/categories
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponseDto>> getCategories() {
        return ResponseEntity.ok(publicService.getAllCategories());
    }

    // GET /api/public/books?page=0&size=10
    @GetMapping("/books")
    public ResponseEntity<Page<BookResponseDto>> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(publicService.getBooks(page, size));
    }

    // GET /api/public/books/{id}
    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(publicService.getBookById(id));
    }
}