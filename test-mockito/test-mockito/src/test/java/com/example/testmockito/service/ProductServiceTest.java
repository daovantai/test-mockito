package com.example.testmockito.service;

import com.example.testmockito.dto.ProductDto;
import com.example.testmockito.model.Product;
import com.example.testmockito.repository.ProductRepository;
import com.example.testmockito.service.impl.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void ProductService_CreateProduct(){
        Product product= new Product("pro1",1);
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        ProductDto productDto= new ProductDto("pro2",2);
        ProductDto saveProduct=productService.save(productDto);
        Assertions.assertThat(saveProduct).isNotNull();
    }

    @Test
    public void ProductService_GetById(){
        Product product= new Product("mockitoo",1234);
        when(productRepository.findById(Long.valueOf(8))).thenReturn(Optional.ofNullable(product));
        Product newProduct= productService.getProductById(Long.valueOf(8));
        Assertions.assertThat(newProduct).isNotNull();
    }

    @Test
    public void ProductService_Update(){
        Product product= new Product("mockito",123);
        ProductDto productDto= new ProductDto("mockito",123);
        when(productRepository.findById(8L)).thenReturn(Optional.ofNullable(product));
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
        ProductDto update= productService.updateProduct(productDto, 8L);
        Assertions.assertThat(update).isNotNull();
    }

    @Test
    public void ProductService_Delete(){
//        Product product= new Product("mockito",123);
//
//        when(productRepository.findById(8L)).thenReturn(Optional.ofNullable(product));
//        assertAll(() -> productService.delete(8L));
        productService.delete(Long.valueOf(4));
    }

}
