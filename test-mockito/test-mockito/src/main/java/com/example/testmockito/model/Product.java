package com.example.testmockito.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@RequiredArgsConstructor
@Entity
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameproduct;
    private int price;

    public Product(Long id, String nameproduct, int price) {
        this.id=id;
        this.nameproduct = nameproduct;
        this.price = price;
    }

    public Product(String nameproduct, int price) {
        this.nameproduct = nameproduct;
        this.price = price;
    }
}