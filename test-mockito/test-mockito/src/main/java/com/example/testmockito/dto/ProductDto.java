package com.example.testmockito.dto;

import lombok.Data;

@Data

public class ProductDto {
    private Long id;
    private String nameproduct;
    private int price;

    public ProductDto() {
    }

    public ProductDto(String nameproduct, int price) {
        this.nameproduct = nameproduct;
        this.price = price;
    }

    public ProductDto(Long id, String nameproduct, int price) {
        this.id = id;
        this.nameproduct = nameproduct;
        this.price = price;
    }
}
