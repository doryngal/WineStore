package com.petproject.WineStore.dto.response;

import lombok.Data;

@Data
public class PaymentResponse {
    private String paymentMethod;
    private Integer amount;
}
