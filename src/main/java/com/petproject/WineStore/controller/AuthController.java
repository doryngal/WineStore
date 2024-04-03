package com.petproject.WineStore.controller;

import com.petproject.WineStore.dto.request.LoginRequest;
import com.petproject.WineStore.dto.request.RegistrationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
@Tag(name = "Authentication", description = "REST API for Authentication")
public interface AuthController {
    @Operation(summary = "Authenticate user", description = "Authenticates a user with the provided login credentials.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully authenticated user"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/login")
    ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest);

    @Operation(summary = "Register a new user", description = "Registers a new user with the provided user data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully registered user"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/signup")
    ResponseEntity<String> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest);
}
