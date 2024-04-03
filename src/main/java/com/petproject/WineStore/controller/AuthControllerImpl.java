package com.petproject.WineStore.controller;

import com.petproject.WineStore.dto.request.LoginRequest;
import com.petproject.WineStore.dto.request.RegistrationRequest;
import com.petproject.WineStore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final UserService userService;

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        return userService.authenticate(loginRequest);
    }

    @Override
    public ResponseEntity<String> registerUser(RegistrationRequest registrationRequest) {
        return userService.register(registrationRequest.toUser());
    }

}
