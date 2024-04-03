package com.petproject.WineStore.controller;

import com.petproject.WineStore.dto.request.OrderRequest;
import com.petproject.WineStore.dto.request.ReviewRequest;
import com.petproject.WineStore.dto.response.WineResponse;
import com.petproject.WineStore.model.Order;
import com.petproject.WineStore.service.OrderService;
import com.petproject.WineStore.service.ReviewService;
import com.petproject.WineStore.service.WineService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserControllerImpl implements UserController{
    private final WineService wineService;
    private final OrderService orderService;

    //Wine
    @Override
    public ResponseEntity<List<WineResponse>> getAllWine() {
        return wineService.getAllWine();
    }

    @Override
    public ResponseEntity<?> getWineById(Long wineId) {
        return wineService.getWineById(wineId);
    }

    //Order
    @Override
    public ResponseEntity<?> getOrder(Long orderId) {
        return orderService.getOrder(orderId);
    }

    @Override
    public Order getUserOrdersList() {
        return orderService.getUserOrdersList();
    }

    @Override
    public Long postOrder(OrderRequest orderRequest) {
        return orderService.postOrder(orderRequest);
    }

    //Review
    private final ReviewService reviewService;
    @Override
    public ResponseEntity<String> addReview(ReviewRequest review, Long wineId) {
        return reviewService.addReview(review.toReview(), wineId);
    }

    @Override
    public ResponseEntity<String> editReview(ReviewRequest newReview, Long reviewId) {
        return reviewService.editReview(newReview.toReview(), reviewId);
    }

    @Override
    public ResponseEntity<String> deleteReview(Long reviewId) {
        return reviewService.deleteReview(reviewId);
    }
}
