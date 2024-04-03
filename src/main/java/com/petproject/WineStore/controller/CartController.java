package com.petproject.WineStore.controller;

import com.petproject.WineStore.dto.response.CartResponse;
import com.petproject.WineStore.dto.response.WineResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cart")
@Tag(name = "Cart", description = "REST API for Cart")
public interface CartController {
    @Operation(summary = "Get wine items in cart", description = "Retrieve wine items currently in the cart.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved wine items in cart",
                    content = @Content(schema = @Schema(implementation = CartResponse.class))),
            @ApiResponse(responseCode = "404", description = "Cart is empty")
    })
    @GetMapping("/items")
    ResponseEntity<List<CartResponse>> getWineInCart();

    @Operation(summary = "Add wine to cart", description = "Add a specific wine to the cart.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wine successfully added to cart"),
            @ApiResponse(responseCode = "404", description = "Wine not found"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add")
    ResponseEntity<String> addWineToCart(
            @RequestParam("wineId") Long wineId,
            @RequestParam("total") Integer total);

    @Operation(summary = "Remove wine from cart", description = "Remove a specific wine from the cart.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wine successfully removed from cart"),
            @ApiResponse(responseCode = "404", description = "Wine not found")
    })
    @DeleteMapping("/remove/{wineId}")
    ResponseEntity<String> removeWineFromCart(@PathVariable("wineId") Long wineId);

    @GetMapping("/get")
    ResponseEntity<String> getW();

    @Operation(summary = "Get all wines", description = "Retrieve a list of all available wines.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of wines",
                    content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "No wines found")
    })
    @GetMapping("/get-all-wine")
    ResponseEntity<List<WineResponse>> getAllWine();
}
