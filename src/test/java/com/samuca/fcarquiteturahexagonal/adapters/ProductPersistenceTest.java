package com.samuca.fcarquiteturahexagonal.adapters;

import com.samuca.fcarquiteturahexagonal.adapters.db.ProductPersistence;
import com.samuca.fcarquiteturahexagonal.adapters.db.ProductRepository;
import com.samuca.fcarquiteturahexagonal.application.Product;
import com.samuca.fcarquiteturahexagonal.application.ProductInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductPersistenceTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void get() {
        Product product = new Product("Product 1", 10.0);
        productRepository.save(product);

        ProductPersistence productPersistence = new ProductPersistence(productRepository);
        ProductInterface productResult = productPersistence.get(product.getId());

        assertEquals(product.getId(), productResult.getId());
        assertEquals(product.getName(), productResult.getName());
        assertEquals(product.getPrice(), productResult.getPrice());
        assertEquals(product.getStatus(), productResult.getStatus());
    }

    @Test
    void save() {
        ProductInterface product = new Product("Product 1", 10.0);

        ProductPersistence productPersistence = new ProductPersistence(productRepository);
        ProductInterface productResult = productPersistence.save(product);

        assertEquals(product.getId(), productResult.getId());
        assertEquals(product.getName(), productResult.getName());
        assertEquals(product.getPrice(), productResult.getPrice());
        assertEquals(product.getStatus(), productResult.getStatus());
    }
}