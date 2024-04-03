package com.petproject.WineStore.dto.response;

import com.petproject.WineStore.model.Cart;
import lombok.Data;

@Data
public class CartResponse {
    private Integer quantity;
    private WineResponse wineResponse;

    public static CartResponse toCartResponse(Cart cart) {
        CartResponse dto = new CartResponse();
        dto.setQuantity(cart.getQuantity());
        dto.setWineResponse(WineResponse.toWineResponse(cart.getWine()));
        return dto;
    }
}
