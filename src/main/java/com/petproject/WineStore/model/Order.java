package com.petproject.WineStore.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "post_index", nullable = false)
    private Integer postIndex;

    @ManyToMany
    private List<Cart> carts = new ArrayList<>();

    @ManyToOne
    private User user;
}
