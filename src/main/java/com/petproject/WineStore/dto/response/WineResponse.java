package com.petproject.WineStore.dto.response;

import com.petproject.WineStore.model.Wine;
import lombok.Data;

@Data
public class WineResponse {
    private Long wineId;
    private String name;
    private String origin;
    private String type;
    private double price;

    public static WineResponse toWineResponse(Wine wine) {
        WineResponse dto = new WineResponse();
        dto.setWineId(wine.getId());
        dto.setName(wine.getName());
        dto.setOrigin(wine.getOrigin());
        dto.setType(wine.getType().getDescription());
        dto.setPrice(wine.getPrice());
        return dto;
    }
}
