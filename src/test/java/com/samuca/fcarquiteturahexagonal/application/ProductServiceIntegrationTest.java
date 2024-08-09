package com.samuca.fcarquiteturahexagonal.application;

import com.samuca.fcarquiteturahexagonal.adapters.db.ProductPersistence;
import com.samuca.fcarquiteturahexagonal.adapters.db.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class ProductServiceIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void get() {
        Product product = new Product("Product 1", 10.0);
        productRepository.save(product);

        ProductPersistence persistence = new ProductPersistence(productRepository);
        ProductServiceInterface productService = new ProductService(persistence);
        ProductInterface productResult = productService.get(product.getId());

        assertNotNull(productResult.getId());
        assertEquals("Product 1", productResult.getName());
        assertEquals(10.0, productResult.getPrice());
        assertEquals(StatusEnum.DISABLED.getStatus(), productResult.getStatus());
    }

    @Test
    void create() {
        ProductPersistence persistence = new ProductPersistence(productRepository);
        ProductServiceInterface productService = new ProductService(persistence);

        ProductInterface productResult = productService.create("Product 1", 10.0);

        assertNotNull(productResult.getId());
        assertEquals("Product 1", productResult.getName());
        assertEquals(10.0, productResult.getPrice());
        assertEquals(StatusEnum.DISABLED.getStatus(), productResult.getStatus());
    }

    @Test
    void enable() {
        Product product = new Product("Product 1", 10.0);
        productRepository.save(product);

        ProductPersistence persistence = new ProductPersistence(productRepository);
        ProductServiceInterface productService = new ProductService(persistence);

        ProductInterface productResult = productService.enable(product);

        assertEquals(StatusEnum.ENABLED.getStatus(), productResult.getStatus());
    }

    @Test
    void disable() {
        Product product = new Product("Product 1");
        productRepository.save(product);

        ProductPersistence persistence = new ProductPersistence(productRepository);
        ProductServiceInterface productService = new ProductService(persistence);

        ProductInterface productResult = productService.disable(product);

        assertEquals(StatusEnum.DISABLED.getStatus(), productResult.getStatus());
    }
}
