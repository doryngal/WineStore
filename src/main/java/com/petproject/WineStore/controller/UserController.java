package com.petproject.WineStore.controller;

import com.petproject.WineStore.dto.request.OrderRequest;
import com.petproject.WineStore.dto.request.ReviewRequest;
import com.petproject.WineStore.dto.response.WineResponse;
import com.petproject.WineStore.model.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
@Tag(name = "User", description = "REST API for User")
public interface UserController {
    //Wine
    @Operation(summary = "Get all wines", description = "Retrieve a list of all available wines.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of wines",
                    content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "No wines found")
    })
    @GetMapping("/get-all-wine")
    ResponseEntity<List<WineResponse>> getAllWine();

    @Operation(summary = "Get wine by ID", description = "Retrieve wine details by wine ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved wine details",
                    content = @Content(schema = @Schema(implementation = WineResponse.class))),
            @ApiResponse(responseCode = "404", description = "Wine not found")
    })
    @GetMapping("/wine/{wineId}")
    ResponseEntity<?> getWineById(@PathVariable("wineId") Long wineId);

    //Order
    @Operation(summary = "Get order by ID", description = "Retrieve order details by order ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved order details",
                    content = @Content(schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @GetMapping("/order/{orderId}")
    ResponseEntity<?> getOrder(@PathVariable("orderId") Long orderId); //TODO ResponseEntity<>

    @Operation(summary = "Get user's orders", description = "Retrieve a list of orders made by the user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user's orders",
                    content = @Content(schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "404", description = "No orders found")
    })
    @GetMapping("/user")
    Order getUserOrdersList();

    @Operation(summary = "Place a new order", description = "Place a new order with the provided order details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order successfully placed",
                    content = @Content(schema = @Schema(implementation = Long.class))),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/order")
    Long postOrder(@RequestBody OrderRequest orderRequest);

    //Review
    @Operation(summary = "Add a review", description = "Add a new review for a specific wine.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review successfully added"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/review/{wineId}/add")
    ResponseEntity<String> addReview(@RequestBody ReviewRequest review, @PathVariable("wineId") Long wineId);

    @Operation(summary = "Edit a review", description = "Edit an existing review.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review successfully edited"),
            @ApiResponse(responseCode = "404", description = "Review not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PutMapping("/review/{reviewId}")
    ResponseEntity<String> editReview(@RequestBody ReviewRequest newReview, @PathVariable("reviewId") Long reviewId);

    @Operation(summary = "Delete a review", description = "Delete an existing review.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Review not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @DeleteMapping("/review/{reviewId}")
    ResponseEntity<String> deleteReview(@PathVariable("reviewId") Long reviewId);
}
