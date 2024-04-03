package com.petproject.WineStore.service;

import com.petproject.WineStore.dto.request.ReviewRequest;
import com.petproject.WineStore.dto.response.ReviewResponse;
import com.petproject.WineStore.model.Review;
import org.springframework.http.ResponseEntity;

public interface ReviewService {
    ResponseEntity<String> addReview(Review review, Long wineId);
    ResponseEntity<String> editReview(Review newReview, Long reviewId);
    ResponseEntity<String> deleteReview(Long reviewId);
}
