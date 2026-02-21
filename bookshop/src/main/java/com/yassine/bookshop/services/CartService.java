package com.yassine.bookshop.services;

import com.yassine.bookshop.dto.CartResponse;
import com.yassine.bookshop.dto.ItemDto;
import com.yassine.bookshop.entities.*;
import com.yassine.bookshop.mappers.CartMapper;
import com.yassine.bookshop.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final CartMapper cartMapper;

    // ==============================
    // GET USER CART WITH TOTAL
    // ==============================
    public CartResponse getUserCart(String email) {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        List<ItemDto> items = cartItemRepository.findByUser_Id(user.getId()).
                stream()
                .map(cartMapper::toDto)
                .toList();

        BigDecimal total = items.stream()
                .map(item ->
                        item.getUnitPrice().multiply(
                                BigDecimal.valueOf(item.getQuantity())
                        )
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CartResponse(items, total, user.getId());
    }

    // ==============================
    // ADD ITEM
    // ==============================
    public void addToCart(String email, Long bookId, Integer quantity) {

        if (quantity == null || quantity <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        CartItemId id = new CartItemId();
        id.setUserId(user.getId());
        id.setBookId(book.getId());

        CartItem item = new CartItem();
        item.setId(id);
        item.setUser(user);
        item.setBook(book);
        item.setQuantity(quantity);
        item.setUnitPrice(book.getPrice());

        cartItemRepository.save(item);
    }

    // ==============================
    // UPDATE QUANTITY
    // ==============================
    public void updateQuantity(String email, Long bookId, Integer quantity) {

        if (quantity == null || quantity <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        CartItemId id = new CartItemId();
        id.setUserId(user.getId());
        id.setBookId(bookId);

        CartItem item = cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        item.setQuantity(quantity);
        cartItemRepository.save(item);
    }

    // ==============================
    // REMOVE ITEM
    // ==============================
    public void removeItem(String email, Long bookId) {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        CartItemId id = new CartItemId();
        id.setUserId(user.getId());
        id.setBookId(bookId);

        cartItemRepository.deleteById(id);
    }
}