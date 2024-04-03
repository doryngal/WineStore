package com.petproject.WineStore.service.impl;

import com.petproject.WineStore.constant.ErrorMessage;
import com.petproject.WineStore.constant.SuccessMessage;
import com.petproject.WineStore.dto.request.ReviewRequest;
import com.petproject.WineStore.dto.response.ReviewResponse;
import com.petproject.WineStore.model.Review;
import com.petproject.WineStore.model.User;
import com.petproject.WineStore.model.Wine;
import com.petproject.WineStore.repository.ReviewRepository;
import com.petproject.WineStore.repository.WineRepository;
import com.petproject.WineStore.service.ReviewService;
import com.petproject.WineStore.service.UserService;
import com.petproject.WineStore.service.WineService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final UserService userService;
    private final WineRepository wineRepository;
    private final ReviewRepository reviewRepository;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public ResponseEntity<String> addReview(Review review, Long wineId) {
        User user = userService.getAuthenticatedUser();
        Wine wine = wineRepository.findById(wineId).orElse(null);
        if (wine == null) {
            log.error("Wine not found with ID: {}", wineId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.WINE_NOT_FOUND);
        }
        review.setWine(wine);
        review.setUser(user);
        reviewRepository.save(review);
        log.info("Review added successfully for wine ID {} by user: {}", wineId, user.getUsername());
        return ResponseEntity.ok(SuccessMessage.REVIEW_ADDED);
    }

    @Override
    public ResponseEntity<String> editReview(Review newReview, Long reviewId) {
        User user = userService.getAuthenticatedUser();
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review == null) {
            log.error("Review not found with ID: {}", reviewId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.REVIEW_NOT_FOUND);
        }
        if (!review.getUser().equals(user)) {
            log.error("User {} does not have permission to edit review with ID: {}", user.getUsername(), reviewId);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorMessage.NOT_HAVE_PERMISSION);
        }
        review.setComment(newReview.getComment());
        review.setRating(newReview.getRating());
        reviewRepository.save(review);
        log.info("Review edited successfully with ID {} by user: {}", reviewId, user.getUsername());
        return ResponseEntity.ok(SuccessMessage.REVIEW_EDITED);
    }

    @Override
    public ResponseEntity<String> deleteReview(Long reviewId) {
        User user = userService.getAuthenticatedUser();
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review == null) {
            log.error("Review not found with ID: {}", reviewId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.REVIEW_NOT_FOUND);
        }
        if (!review.getUser().equals(user)) {
            log.error("User {} does not have permission to delete review with ID: {}", user.getUsername(), reviewId);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorMessage.NOT_HAVE_PERMISSION);
        }
        reviewRepository.delete(review);
        log.info("Review deleted successfully with ID {} by user: {}", reviewId, user.getUsername());
        return ResponseEntity.ok(SuccessMessage.REVIEW_DELETED);
    }

}
