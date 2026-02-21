package com.yassine.bookshop.controllers;

import com.yassine.bookshop.dto.CartResponse;
import com.yassine.bookshop.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public CartResponse getCart(Authentication authentication) {
        return cartService.getUserCart(authentication.getName());
    }

    @PostMapping("/items")
    public void addItem(Authentication authentication,
                        @RequestParam Long bookId,
                        @RequestParam Integer quantity) {

        cartService.addToCart(authentication.getName(), bookId, quantity);
    }

    @PutMapping("/items/{bookId}")
    public void updateItem(Authentication authentication,
                           @PathVariable Long bookId,
                           @RequestParam Integer quantity) {

        cartService.updateQuantity(authentication.getName(), bookId, quantity);
    }

    @DeleteMapping("/items/{bookId}")
    public void deleteItem(Authentication authentication,
                           @PathVariable Long bookId) {

        cartService.removeItem(authentication.getName(), bookId);
    }
}