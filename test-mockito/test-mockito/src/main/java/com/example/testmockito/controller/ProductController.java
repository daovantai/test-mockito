package com.example.testmockito.controller;

import com.example.testmockito.model.Product;
import com.example.testmockito.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> listAll(){
        return productService.listAll();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        try {
            Product product= productService.getProductById(id);
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/products")
    public void addProduct(@RequestBody Product product){
        productService.save(product);
    }
    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product,@PathVariable Long id){
        try {
            Product existProduct= productService.getProductById(id);
            productService.save(product);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.delete(id);
    }
}
