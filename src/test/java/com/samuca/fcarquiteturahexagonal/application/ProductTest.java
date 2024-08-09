package com.samuca.fcarquiteturahexagonal.application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void enable() {
        ProductInterface product = new Product("Hello", 10);
        product.enable();
        assertEquals(StatusEnum.ENABLED.getStatus(), product.getStatus());

        product = new Product("Hello");
        assertThrows(RuntimeException.class, product::enable, "the price must be greater than zero to enable the product");
    }

    @Test
    void disable() {
        ProductInterface product = new Product("Hello");
        product.disable();
        assertEquals(StatusEnum.DISABLED.getStatus(), product.getStatus());

        product = new Product("Hello", 10);
        assertThrows(RuntimeException.class, product::disable, "the price must be zero to disable the product");
    }

    @Test
    void isValid() {
        ProductInterface product = new Product("Hello", 10);
        assertTrue(product.isValid());

        product = new Product("Hello", -10);
        assertThrows(RuntimeException.class, product::isValid, "the price must be greater than or equal to zero");

        product = new Product("Hello", 10);
        product.enable();
        assertTrue(product.isValid());

        product = new Product("Hello");
        product.disable();
        assertTrue(product.isValid());

        product = new Product("Hello", -10);
        assertThrows(RuntimeException.class, product::isValid, "the price must be greater than or equal to zero");

        product = new Product("12345", "Hello", "", 10);
        assertTrue(product.isValid());

        product = new Product("12345", "Hello", "Invalid", 10);
        assertThrows(RuntimeException.class, product::isValid, "the status must be enabled or disabled");

        product = new Product("", "Hello", "enabled", 10);
        assertFalse(product.isValid());

    }

    @Test
    void getId() {
        ProductInterface product = new Product("Hello", 10);
        assertNotNull(product.getId());
    }

    @Test
    void getName() {
        ProductInterface product = new Product("Hello", 10);
        assertEquals("Hello", product.getName());
    }

    @Test
    void getStatus() {
        ProductInterface product = new Product("Hello", 10);
        assertEquals(StatusEnum.DISABLED.getStatus(), product.getStatus());
    }

    @Test
    void getPrice() {
        ProductInterface product = new Product("Hello", 10);
        assertEquals(10, product.getPrice());
    }

    @Test
    void changePrice() {
        ProductInterface product = new Product("Hello", 10);
        product.changePrice(20);
        assertEquals(20, product.getPrice());

        assertThrows(RuntimeException.class, () -> product.changePrice(-10), "the price must be greater than or equal to zero");
    }
}