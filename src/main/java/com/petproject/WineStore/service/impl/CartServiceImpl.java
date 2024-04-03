package com.petproject.WineStore.service.impl;

import com.petproject.WineStore.constant.ErrorMessage;
import com.petproject.WineStore.constant.SuccessMessage;
import com.petproject.WineStore.dto.response.CartResponse;
import com.petproject.WineStore.model.Cart;
import com.petproject.WineStore.model.User;
import com.petproject.WineStore.model.Wine;
import com.petproject.WineStore.repository.CartRepository;
import com.petproject.WineStore.repository.WineRepository;
import com.petproject.WineStore.service.CartService;
import com.petproject.WineStore.service.UserService;
import com.petproject.WineStore.service.WineService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final UserService userService;
    private final WineRepository wineRepository;
    private final CartRepository cartRepository;

    @Override
    public List<CartResponse> getWineInCart() {
        User user = userService.getAuthenticatedUser();
        List<CartResponse> cartResponses = new ArrayList<>();
        for (Cart cart : user.getCarts()) {
            cartResponses.add(CartResponse.toCartResponse(cart));
        }
        return cartResponses;
    }

    @Override
    @Transactional
    public ResponseEntity<String> addCart(Long wineId, Integer quantity) {
        User user = userService.getAuthenticatedUser();
        Wine wine = wineRepository.findById(wineId).orElse(null);
        if (wine == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.WINE_NOT_FOUND);
        Cart cart = new Cart();
        cart.setWine(wine);
        cart.setQuantity(quantity);
        cart.setUser(user);
        cartRepository.save(cart);
        user.getCarts().add(cart);
        return ResponseEntity.ok(SuccessMessage.WINE_ADDED_TO_CART);
    }

    @Override
    @Transactional
    public ResponseEntity<String> removeCart(Long cartId) {
        User user = userService.getAuthenticatedUser();
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(ErrorMessage.CART_NOT_FOUND);
        user.getCarts().remove(cart);
        cartRepository.delete(cart);
        return ResponseEntity.ok(SuccessMessage.WINE_REMOVE_FROM_CART);
    }
}