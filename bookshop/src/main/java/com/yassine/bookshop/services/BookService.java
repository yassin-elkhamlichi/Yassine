package com.yassine.bookshop.services;

import com.yassine.bookshop.dto.BookCreateDto;
import com.yassine.bookshop.dto.BookResponseDto;
import com.yassine.bookshop.entities.Book;
import com.yassine.bookshop.entities.Category;
import com.yassine.bookshop.repositories.BookRepository;
import com.yassine.bookshop.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookResponseDto addBook(BookCreateDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPrice(dto.getPrice());
        book.setStock(dto.getStock() == null ? 0 : dto.getStock());
        book.setDescription(dto.getDescription());

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Category not found"));
            book.setCategory(category);
        }

        Book saved = bookRepository.save(book);
        return toDto(saved);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
        bookRepository.deleteById(id);
    }

    private BookResponseDto toDto(Book book) {
        BookResponseDto dto = new BookResponseDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPrice(book.getPrice());
        dto.setStock(book.getStock());
        dto.setDescription(book.getDescription());
        dto.setCategoryId(book.getCategory() == null ? null : book.getCategory().getId());
        return dto;
    }
}
