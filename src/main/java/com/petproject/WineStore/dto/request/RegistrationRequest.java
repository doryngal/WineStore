package com.petproject.WineStore.dto.request;

import com.petproject.WineStore.constant.ErrorMessage;
import com.petproject.WineStore.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationRequest {
    @NotEmpty(message = ErrorMessage.EMPTY_USERNAME)
    private String username;
    @NotEmpty(message = ErrorMessage.EMPTY_CITY)
    private String city;
    @NotEmpty(message = ErrorMessage.EMPTY_ADDRESS)
    private String address;
    @Email(message = ErrorMessage.EMAIL_INVALID)
    private String email;
    @NotEmpty(message = ErrorMessage.EMPTY_PHONE_NUMBER)
    private String phoneNumber;
    @Size(min = 6, max = 16, message = ErrorMessage.PASSWORD_CHARACTER_LENGTH)
    private String password;

    public User toUser() {
        User user = new User();
        user.setUsername(this.username);
        user.setCity(this.city);
        user.setAddress(this.address);
        user.setEmail(this.email);
        user.setPhoneNumber(this.phoneNumber);
        user.setPassword(this.password);
        user.setActive(true);
        return user;
    }
}
