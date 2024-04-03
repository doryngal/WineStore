package com.petproject.WineStore.model;

import java.util.Random;

public enum WineType {
    RED("Red Wine"),
    WHITE("White Wine"),
    ROSÉ("Rosé Wine"),
    SPARKLING("Sparkling Wine"),
    DESSERT("Dessert Wine"),
    FORTIFIED("Fortified Wine"),
    NATURAL("Natural Wine"),
    ORGANIC("Organic Wine");

    private final String description;

    WineType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static WineType getRandomWineType() {
        WineType[] wineTypes = WineType.values();
        Random random = new Random();
        int index = random.nextInt(wineTypes.length);
        return wineTypes[index];
    }
}

