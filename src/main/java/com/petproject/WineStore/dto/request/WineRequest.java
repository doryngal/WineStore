package com.petproject.WineStore.dto.request;

import com.petproject.WineStore.model.Wine;
import com.petproject.WineStore.model.WineType;
import lombok.Data;

@Data
public class WineRequest {
    private String name;
    private String origin;
    private WineType type;
    private int year;
    private double price;
    private String description;
    private int stock;

    public Wine toWine() {
        Wine wine = new Wine();
        return to(wine);
    }

    public Wine toWine(Wine wine) {
        return to(wine);
    }

    private Wine to(Wine wine) {
        wine.setName(this.name);
        wine.setOrigin(this.origin);
        wine.setType(this.type);
        wine.setYear(this.year);
        wine.setPrice(this.price);
        wine.setDescription(this.description);
        wine.setStock(this.stock);
        return wine;
    }
}
