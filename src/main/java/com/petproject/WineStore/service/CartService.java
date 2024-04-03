package com.petproject.WineStore.service;

import com.petproject.WineStore.dto.response.CartResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {
    List<CartResponse> getWineInCart();

    ResponseEntity<String> addCart(Long wineId, Integer total);

    ResponseEntity<String> removeCart(Long wineId);
}
