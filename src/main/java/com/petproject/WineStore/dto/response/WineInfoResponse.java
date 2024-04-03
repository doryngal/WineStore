package com.petproject.WineStore.dto.response;

import com.petproject.WineStore.model.Wine;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class WineInfoResponse {
    private String name;
    private String origin;
    private String type;
    private int year;
    private double price;
    private String description;
    private int stock;
    private List<ReviewResponse> reviews;

    public static WineInfoResponse toWineInfo(Wine wine) {
        WineInfoResponse response = new WineInfoResponse();
        response.setName(wine.getName());
        response.setOrigin(wine.getOrigin());
        response.setType(wine.getType().getDescription());
        response.setYear(wine.getYear());
        response.setPrice(wine.getPrice());
        response.setDescription(wine.getDescription());
        response.setStock(wine.getStock());

        List<ReviewResponse> reviewResponses = wine.getReviews().stream()
                .map(ReviewResponse::toReviewResponse)
                .collect(Collectors.toList());
        response.setReviews(reviewResponses);
        return response;
    }
}
