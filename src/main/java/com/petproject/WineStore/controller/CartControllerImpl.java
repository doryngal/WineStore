package com.petproject.WineStore.controller;

import com.petproject.WineStore.dto.response.CartResponse;
import com.petproject.WineStore.dto.response.WineResponse;
import com.petproject.WineStore.service.CartService;
import com.petproject.WineStore.service.WineService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartControllerImpl implements CartController{
    private final CartService cartService;
    @Override
    public ResponseEntity<List<CartResponse>> getWineInCart() {
        return ResponseEntity.ok(cartService.getWineInCart());
    }

    @Override
    public ResponseEntity<String> addWineToCart(Long wineId, Integer total) {
        return cartService.addCart(wineId, total);
    }

    @Override
    public ResponseEntity<String> removeWineFromCart(Long wineId) {
        return cartService.removeCart(wineId);
    }


    @Override
    public ResponseEntity<String> getW() {
        return ResponseEntity.ok("hi");
    }

    private final WineService wineService;

    @Override
    public ResponseEntity<List<WineResponse>> getAllWine() {
        return wineService.getAllWine();
    }
}
