package com.petproject.WineStore.dto.request;

import com.petproject.WineStore.constant.ErrorMessage;
import com.petproject.WineStore.model.Order;
import com.petproject.WineStore.model.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrderRequest {
    @NotEmpty(message = ErrorMessage.EMPTY_CITY)
    private String city;
    @NotEmpty(message = ErrorMessage.EMPTY_ADDRESS)
    private String address;
    @NotEmpty(message = ErrorMessage.EMPTY_PHONE_NUMBER)
    private String phoneNumber;
    @Size(min = 5, max = 5, message = ErrorMessage.EMPTY_POST_INDEX)
    private Integer postIndex;

    public Order toOrder(User user) {
        Order order = new Order();
        order.setUsername(user.getUsername());
        order.setEmail(user.getEmail());
        order.setCarts(user.getCarts());

        order.setCity(this.city);
        order.setAddress(this.address);
        order.setPhoneNumber(this.phoneNumber);
        order.setPostIndex(this.postIndex);

        return order;
    }
}
