package com.samuca.fcarquiteturahexagonal.application;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Test
    void get() {
        ProductInterface product = new Product("Product 1", 10.0);
        ProductPersistenceInterface persistence = Mockito.mock(ProductPersistenceInterface.class);
        Mockito.when(persistence.get("1")).thenReturn(product);

        ProductServiceInterface productService = new ProductService(persistence);
        ProductInterface productResult = productService.get("1");

        Mockito.verify(persistence).get("1");
        assertEquals(product, productResult);
    }

    @Test
    void create() {
        ProductInterface product = new Product("Product 1", 10.0);
        ProductPersistenceInterface persistence = Mockito.mock(ProductPersistenceInterface.class);
        Mockito.when(persistence.save(Mockito.any(ProductInterface.class))).thenReturn(product);

        ProductServiceInterface productService = new ProductService(persistence);
        ProductInterface productResult = productService.create("Product 1", 10.0);

        Mockito.verify(persistence).save(Mockito.any(ProductInterface.class));
        assertNotNull(productResult.getId());
        assertEquals("Product 1", productResult.getName());
        assertEquals(10.0, productResult.getPrice());
        assertEquals(StatusEnum.DISABLED.getStatus(), productResult.getStatus());
    }

    @Test
    void enable() {
        ProductInterface product = new Product("Product 1", 10.0);
        ProductPersistenceInterface persistence = Mockito.mock(ProductPersistenceInterface.class);
        Mockito.when(persistence.save(Mockito.any(ProductInterface.class))).thenReturn(product);

        ProductServiceInterface productService = new ProductService(persistence);
        ProductInterface productResult = productService.enable(product);

        Mockito.verify(persistence).save(Mockito.any(ProductInterface.class));
        assertEquals(StatusEnum.ENABLED.getStatus(), productResult.getStatus());
    }

    @Test
    void disable() {
        ProductInterface product = new Product("Product 1");
        ProductPersistenceInterface persistence = Mockito.mock(ProductPersistenceInterface.class);
        Mockito.when(persistence.save(Mockito.any(ProductInterface.class))).thenReturn(product);

        ProductServiceInterface productService = new ProductService(persistence);
        ProductInterface productResult = productService.disable(product);

        Mockito.verify(persistence).save(Mockito.any(ProductInterface.class));
        assertEquals(StatusEnum.DISABLED.getStatus(), productResult.getStatus());
    }
}