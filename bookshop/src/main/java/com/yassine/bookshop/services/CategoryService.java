package com.yassine.bookshop.services;

import com.yassine.bookshop.dto.CategoryCreateDto;
import com.yassine.bookshop.entities.Category;
import com.yassine.bookshop.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category addCategory(CategoryCreateDto dto) {
        if (categoryRepository.existsByName(dto.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category already exists");
        }
        Category category = new Category();
        category.setName(dto.getName());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
        categoryRepository.deleteById(id);
    }
}
