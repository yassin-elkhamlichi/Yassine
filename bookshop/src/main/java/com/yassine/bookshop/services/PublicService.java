package com.yassine.bookshop.services;

import com.yassine.bookshop.dto.BookResponseDto;
import com.yassine.bookshop.dto.CategoryResponseDto;
import com.yassine.bookshop.entities.Book;
import com.yassine.bookshop.entities.Category;
import com.yassine.bookshop.mappers.BookMapper;
import com.yassine.bookshop.repositories.BookRepository;
import com.yassine.bookshop.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PublicService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    // ── Categories ──────────────────────────────────────────────────────────

    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::mapCategory)
                .collect(Collectors.toList());
    }

    // ── Books ────────────────────────────────────────────────────────────────

    public Page<BookResponseDto> getBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable)
                .map(bookMapper::toResponseDto);
    }

    public BookResponseDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Book not found with id: " + id
                ));
        return mapBook(book);
    }

    // ── Mappers ──────────────────────────────────────────────────────────────

    private BookResponseDto mapBook(Book book) {
        BookResponseDto dto = new BookResponseDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPrice(book.getPrice());
        dto.setStock(book.getStock());
        dto.setDescription(book.getDescription());
        if (book.getCategory() != null) {
            dto.setCategoryName(book.getCategory().getName());
        }
        return dto;
    }

    private CategoryResponseDto mapCategory(Category category) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
}