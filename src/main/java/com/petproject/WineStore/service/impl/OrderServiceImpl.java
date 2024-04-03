package com.petproject.WineStore.service.impl;

import com.petproject.WineStore.constant.ErrorMessage;
import com.petproject.WineStore.dto.request.OrderRequest;
import com.petproject.WineStore.model.Cart;
import com.petproject.WineStore.model.Order;
import com.petproject.WineStore.model.User;
import com.petproject.WineStore.repository.OrderRepository;
import com.petproject.WineStore.service.OrderService;
import com.petproject.WineStore.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final UserService userService;
    private final OrderRepository orderRepository;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public ResponseEntity<?> getOrder(Long orderId) {
        User user = userService.getAuthenticatedUser();
        Order order = orderRepository.findByIdAndUserId(orderId, user.getId());
        if (order == null) {
            log.error("Order not found with ID {} for user: {}", orderId, user.getUsername());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.ORDER_NOT_FOUND);
        }
        log.info("Order retrieved successfully with ID {} for user: {}", orderId, user.getUsername());
        return ResponseEntity.ok(order);
    }

    @Override
    public Order getUserOrdersList() {
        User user = userService.getAuthenticatedUser();
        return orderRepository.findOrderByUserId(user.getId());
    }

    @Override
    @Transactional
    public Long postOrder(OrderRequest orderRequest) {
        User user = userService.getAuthenticatedUser();
        Order order = orderRequest.toOrder(user);
        order.setTotalPrice(CalculationTotalPrice(order.getCarts()));
        orderRepository.save(order);
        user.getCarts().clear();
        log.info("Order placed successfully with ID {} for user: {}",
                order.getId(), user.getUsername());
//        Map<String, Object> attributes = new HashMap<>();
//        attributes.put("order", order);
//        mailService.sendMessageHtml(order.getEmail(), "Order #" + order.getId(), "order-template", attributes);
        return order.getId();
    }

    private Double CalculationTotalPrice(List<Cart> carts) {
        Double totalPrice = null;
        for (Cart cart : carts) {
            totalPrice += cart.getQuantity() * cart.getWine().getPrice();
        }
        return totalPrice;
    }
}
