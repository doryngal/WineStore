package com.petproject.WineStore.dto.request;

import com.petproject.WineStore.constant.ErrorMessage;
import com.petproject.WineStore.model.Review;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReviewRequest {
    @NotEmpty(message = ErrorMessage.EMPTY_RATING)
    private int rating;
    @Size(min = 10, max = 200, message = ErrorMessage.EMPTY_COMMENT)
    private String comment;

    public Review toReview() {
        Review review = new Review();
        review.setRating(this.rating);
        review.setComment(this.comment);
        return review;
    }
}
