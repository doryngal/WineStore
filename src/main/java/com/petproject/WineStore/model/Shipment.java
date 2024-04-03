package com.petproject.WineStore.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Shipment extends BaseEntity {
    private String address;
    private String city;
    private String state;
    private String country;
    private String zip_code;
}
