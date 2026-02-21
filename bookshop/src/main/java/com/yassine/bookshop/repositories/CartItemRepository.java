package com.yassine.bookshop.repositories;

import com.yassine.bookshop.entities.CartItem;
import com.yassine.bookshop.entities.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {

    List<CartItem> findByUser_Id(Long userId);

}