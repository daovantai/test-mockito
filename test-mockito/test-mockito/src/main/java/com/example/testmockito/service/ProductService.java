package com.example.testmockito.service;


import com.example.testmockito.dto.ProductDto;
import com.example.testmockito.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<Product> listAll();
    ProductDto save(ProductDto productDto);
    Product getProductById(Long id);
    void delete(Long id);
    ProductDto updateProduct(ProductDto productDto,Long id);
}
