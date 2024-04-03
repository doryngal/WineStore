package com.petproject.WineStore.service;

import com.petproject.WineStore.dto.request.ChangePasswordRequest;
import com.petproject.WineStore.dto.request.LoginRequest;
import com.petproject.WineStore.dto.request.UserRequest;
import com.petproject.WineStore.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    User getAuthenticatedUser();
    ResponseEntity<String> register(User user);
    ResponseEntity<?> authenticate(LoginRequest loginRequest);
    ResponseEntity<String> changePassword(ChangePasswordRequest request);

    ResponseEntity<String> editProfile(UserRequest editProfile);
    ResponseEntity<String> deleteProfile();
}
