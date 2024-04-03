package com.petproject.WineStore.service.impl;

import com.petproject.WineStore.constant.ErrorMessage;
import com.petproject.WineStore.constant.SuccessMessage;
import com.petproject.WineStore.dto.request.ChangePasswordRequest;
import com.petproject.WineStore.dto.request.LoginRequest;
import com.petproject.WineStore.model.Role;
import com.petproject.WineStore.model.User;
import com.petproject.WineStore.repository.UserRepository;
import com.petproject.WineStore.security.jwt.JwtTokenProvider;
import com.petproject.WineStore.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@EnableJpaAuditing
public class UserServiceImpl implements UserService {
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            String email = userDetails.getUsername();
            return userRepository.findByEmail(email)
                    .orElseThrow(() -> new IllegalStateException(ErrorMessage.USER_NOT_FOUND_BY_EMAIL));
        }
        throw new IllegalStateException(ErrorMessage.USER_NOT_AUTHENTICATED);
    }
    @Override
    public ResponseEntity<String> register(User user) {
        log.info("Attempting to register a user with the email: {}", user.getEmail());
        if (userRepository.existsByEmail(user.getEmail())) {
            log.warn("Registration failed: Email {} is already taken", user.getEmail());
            return ResponseEntity
                    .badRequest()
                    .body(ErrorMessage.EMAIL_IN_USE);
        }

        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            log.warn("Registration failed: Email {} is already taken", user.getEmail());
            return ResponseEntity
                    .badRequest()
                    .body(ErrorMessage.PHONE_NUMBER_IN_USE);
        }
        user.setRoles(Collections.singleton(Role.ROLE_USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("User successfully registered with email: {}", user.getEmail());

        return ResponseEntity
                .ok()
                .body(SuccessMessage.USER_REGISTERED);
    }

    @Override
    public ResponseEntity<?> authenticate(LoginRequest loginRequest) {
        log.info("Attempted to authenticate a user with email: {}", loginRequest.getEmail());
        try {
            Authentication authentication = authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
            ));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenProvider.generateToken(authentication);

            log.info("User successfully authenticated with email: {}", loginRequest.getEmail());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            log.error("Authentication failed: Invalid username or password for email: {}", loginRequest.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessage.INVALID_EMAIL_OR_PASSWORD);
        } catch (AuthenticationException e) {
            log.error("Failed to authenticate for username: {}", loginRequest.getEmail(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.AUTHENTICATION_FAILED);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<String> changePassword(ChangePasswordRequest request) {
        if (request.getPassword() != null && !request.getPassword().equals(request.getPassword2())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.PASSWORDS_DO_NOT_MATCH);
        }
        User user = getAuthenticatedUser();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return ResponseEntity.ok(SuccessMessage.PASSWORD_CHANGED);
    }

    //TODO edit user profile


    //TODO delete user profile
}
