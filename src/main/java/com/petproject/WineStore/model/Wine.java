package com.petproject.WineStore.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Wine extends BaseEntity {
    @Column(name = "name")
    private String name; // Название вина
    @Column(name = "origin")
    private String origin; // Страна происхождения вина
    @Enumerated(EnumType.STRING)
    private WineType type;; // Тип вина (красное, белое, розовое и т.д.)
    @Column(name = "year")
    private int year; // Год урожая вина
    @Column(name = "price")
    private double price; // Цена за бутылку вина
    @Column(name = "description")
    private String description; // Описание вина
    @Column(name = "stock")
    private int stock; // Количество бутылок вина на складе

    @OneToMany(mappedBy = "wine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> reviews;
}
