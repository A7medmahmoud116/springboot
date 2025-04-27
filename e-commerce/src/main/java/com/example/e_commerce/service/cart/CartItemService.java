package com.example.e_commerce.service.cart;


import com.example.e_commerce.exceptions.ResourceNotFoundException;
import com.example.e_commerce.model.Cart;
import com.example.e_commerce.model.CartItem;
import com.example.e_commerce.model.Product;
import com.example.e_commerce.repository.CartItemRepository;
import com.example.e_commerce.repository.CartRepository;
import com.example.e_commerce.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final IProductService productService;
    private final CartService cartService;


    @Override
    public void addItemToCart(Long cartId, Long productId, int quantity) {
        // 1- get cart
        // 2- get product
        // 3- check if product already exists in the cart
        // 4- if exists, update quantity
        // 5- if not, add to cart
        Cart cart = cartService.getCart(cartId);
        Product product = productService.getProductById(productId);
        CartItem cartItem = cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId() == productId)
                .findFirst()
                .orElse(new CartItem());
        if (cartItem.getId() == null) {
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItem.setUnitPrice(product.getPrice());
        }else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cartItem.setTotalPrice();
        cart.addItem(cartItem);
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
    }
//@Override
//public void addItemToCart(Long cartId, Long productId, int quantity) {
//    Cart cart = cartServise.getCart(cartId);
//    Product product = productService.getProductById(productId);
//
//    cart.getItems().stream()
//            .filter(item -> item.getProduct().getId()== productId)
//            .findFirst()
//            .ifPresentOrElse(
//                    existingItem -> {
//                        existingItem.setQuantity(existingItem.getQuantity() + quantity);
//                        existingItem.setUnitPrice(product.getPrice());
//                        existingItem.setTotalPrice();
//                    },
//                    () -> {
//                        CartItem newItem = new CartItem();
//                        newItem.setProduct(product);
//                        newItem.setQuantity(quantity);
//                        newItem.setUnitPrice(product.getPrice());
//                        newItem.setTotalPrice();
//                        newItem.setCart(cart);
//                        cart.addItem(newItem);
//                    }
//            );
//
//    cartRepository.save(cart);
//}

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {
    Cart cart = cartService.getCart(cartId);
    CartItem itemToRemove = getCartItem(cartId, productId);
    cart.removeItem(itemToRemove);
    cartRepository.save(cart);
    }

    @Override
    public void updateItemQuantity(Long cartId, Long productId, int quantity) {
        Cart cart = cartService.getCart(cartId);
        cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId() == productId)
                .findFirst()
                .ifPresent(item -> {
                    item.setQuantity(quantity);
                    item.setUnitPrice(item.getProduct().getPrice());
                    item.setTotalPrice();
                });
        BigDecimal totalAmount = cart.getItems().stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);
    }
    @Override
    public CartItem getCartItem(Long cartId, Long productId) {
        Cart cart = cartService.getCart(cartId);
        return  cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId() == productId)
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Item not found"));
    }
}
