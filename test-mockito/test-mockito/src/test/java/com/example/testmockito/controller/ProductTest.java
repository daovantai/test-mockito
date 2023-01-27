package com.example.testmockito.controller;

import com.example.testmockito.dto.ProductDto;
import com.example.testmockito.model.Product;
import com.example.testmockito.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ProductTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private Product product;
    private ProductDto productDto;
    @BeforeEach
    public void init(){
        product= Product.builder().id(1L).nameproduct("mockito").price(1).build();
        productDto= new ProductDto(2L,"pro2",2);
    }
    @Test
    public void CreateProductController() throws Exception{

        when(productService.save(productDto)).thenReturn(productDto);
        ResultActions response= mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)));
        response.andExpect(status().isOk());

    }
    @Test
    public void listAllProduct() throws  Exception{
        Product product1= new Product(1L,"p1",1);
        Product product2= new Product(2L,"p2",2);
        Product product3= new Product(3L,"p3",3);
        List<Product> products= Arrays.asList(product1,product2,product3);
        when(productService.listAll()).thenReturn(products);
        ResultActions response= mockMvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    public void getProductById() throws Exception{
        Product product1= new Product(1L,"p1",1);
        when(productService.getProductById(1L)).thenReturn(product1);
        mockMvc.perform(get("/products/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
    @Test
    public void deleteProductById() throws Exception{
        Long id= 1L;
        doNothing().when(productService).delete(1L);
        mockMvc.perform(delete("/products/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }
}
