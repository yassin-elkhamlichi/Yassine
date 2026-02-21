package com.yassine.bookshop.repositories;

import com.yassine.bookshop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}