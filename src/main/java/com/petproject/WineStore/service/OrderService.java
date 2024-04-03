package com.petproject.WineStore.service;

import com.petproject.WineStore.dto.request.OrderRequest;
import com.petproject.WineStore.dto.response.WineResponse;
import com.petproject.WineStore.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    ResponseEntity<?> getOrder(Long orderId);
    Order getUserOrdersList();
    Long postOrder(OrderRequest orderRequest);
}
