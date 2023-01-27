package com.example.testmockito.service.impl;


import com.example.testmockito.dto.ProductDto;
import com.example.testmockito.model.Product;
import com.example.testmockito.repository.ProductRepository;
import com.example.testmockito.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> listAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product product= new Product();
        product.setNameproduct(productDto.getNameproduct());
        product.setPrice(productDto.getPrice());
        Product newProduct=productRepository.save(product);
        ProductDto response= new ProductDto();
        response.setId(newProduct.getId());
        response.setNameproduct(newProduct.getNameproduct());
        response.setPrice(newProduct.getPrice());
        return response;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Long id) {
        Product product= productRepository.findById(id).orElse(null);
        product.setNameproduct(productDto.getNameproduct());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);

        ProductDto response= new ProductDto();
        response.setId(product.getId());
        response.setNameproduct(product.getNameproduct());
        response.setPrice(product.getPrice());
        return response;
    }
}
