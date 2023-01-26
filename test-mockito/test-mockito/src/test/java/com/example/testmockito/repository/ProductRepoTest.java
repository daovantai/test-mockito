package com.example.testmockito.repository;

import com.example.testmockito.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class ProductRepoTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
//    @Rollback(value = false)
    public void createProduct(){
        Product product= new Product("mockito",123);
        Product product1=Product.builder().nameproduct("test-moc").price(23).build();
        Product savedProduct=productRepository.save(product);
        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void listAll(){
        Product product= new Product("p1",1);
        Product product1= new Product("p2",2);

        productRepository.save(product);
        productRepository.save(product1);

        List<Product> productList= productRepository.findAll();
        Assertions.assertThat(productList).isNotNull();
        Assertions.assertThat(productList.size()).isEqualTo(10);
    }
}
