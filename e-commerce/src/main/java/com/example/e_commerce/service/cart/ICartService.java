package com.example.e_commerce.service.cart;

import com.example.e_commerce.model.Cart;
import com.example.e_commerce.model.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);


    Cart initializeNewCard(User user);

    Cart getCartByUserId(Long userId);
}
