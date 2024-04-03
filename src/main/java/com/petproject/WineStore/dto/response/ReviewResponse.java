package com.petproject.WineStore.dto.response;

import com.petproject.WineStore.model.Review;
import lombok.Data;

@Data
public class ReviewResponse {
    private String username;
    private int rating;
    private String comment;

    public static ReviewResponse toReviewResponse(Review review) {
        ReviewResponse response = new ReviewResponse();
        response.setUsername(review.getUser().getUsername());
        response.setRating(review.getRating());
        response.setComment(review.getComment());

        return response;
    }
}
