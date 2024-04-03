package com.petproject.WineStore.dto.request;

import com.petproject.WineStore.constant.ErrorMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginRequest {
    @Email(message = ErrorMessage.EMAIL_INVALID)
    private String email;
    @Size(min = 6, max = 16, message = ErrorMessage.PASSWORD_CHARACTER_LENGTH)
    private String password;
}
