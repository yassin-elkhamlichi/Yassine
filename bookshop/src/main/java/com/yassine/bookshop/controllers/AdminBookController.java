package com.yassine.bookshop.controllers;

import com.yassine.bookshop.dto.BookCreateDto;
import com.yassine.bookshop.dto.BookResponseDto;
import com.yassine.bookshop.services.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/books")
@AllArgsConstructor
public class AdminBookController {

    private final BookService bookService;

    @PostMapping
    public BookResponseDto addBook(@Valid @RequestBody BookCreateDto dto) {
        return bookService.addBook(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
