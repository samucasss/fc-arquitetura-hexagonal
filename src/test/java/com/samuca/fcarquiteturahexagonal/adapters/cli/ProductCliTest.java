package com.samuca.fcarquiteturahexagonal.adapters.cli;

import com.samuca.fcarquiteturahexagonal.application.Product;
import com.samuca.fcarquiteturahexagonal.application.ProductInterface;
import com.samuca.fcarquiteturahexagonal.application.ProductServiceInterface;
import com.samuca.fcarquiteturahexagonal.application.StatusEnum;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ProductCliTest {

    @Test
    void runCreate() {
        ProductInterface product = new Product("1", "Product 1", StatusEnum.DISABLED.getStatus(), 10);
        ProductServiceInterface productService = Mockito.mock(ProductServiceInterface.class);
        Mockito.when(productService.create("Product 1", 10)).thenReturn(product);

        ProductCli productCli = new ProductCli(productService);
        String message = productCli.run("create", "1", "Product 1", 10.0);
        assertEquals("Product ID 1 with the name Product 1 has been created with the price 10,00 and status disabled", message);
    }

    @Test
    void runEnable() {
        ProductInterface product = new Product("1", "Product 1", StatusEnum.DISABLED.getStatus(), 10);
        ProductServiceInterface productService = Mockito.mock(ProductServiceInterface.class);
        Mockito.when(productService.get("1")).thenReturn(product);
        Mockito.when(productService.enable(product)).thenReturn(new Product("1", "Product 1", StatusEnum.ENABLED.getStatus(), 10));

        ProductCli productCli = new ProductCli(productService);
        String message = productCli.run("enable", "1", null, 0);
        assertEquals("Product Product 1 has been enabled", message);
    }

    @Test
    void runDisable() {
        ProductInterface product = new Product("1", "Product 1", StatusEnum.ENABLED.getStatus(), 10);
        ProductServiceInterface productService = Mockito.mock(ProductServiceInterface.class);
        Mockito.when(productService.get("1")).thenReturn(product);
        Mockito.when(productService.disable(product)).thenReturn(new Product("1", "Product 1", StatusEnum.DISABLED.getStatus(), 10));

        ProductCli productCli = new ProductCli(productService);
        String message = productCli.run("disable", "1", null, 0);
        assertEquals("Product Product 1 has been disabled", message);
    }

    @Test
    void runDefault() {
        ProductInterface product = new Product("1", "Product 1", StatusEnum.DISABLED.getStatus(), 10);
        ProductServiceInterface productService = Mockito.mock(ProductServiceInterface.class);
        Mockito.when(productService.get("1")).thenReturn(product);

        ProductCli productCli = new ProductCli(productService);
        String message = productCli.run("default", "1", null, 0);
        assertEquals("Product ID: 1\nName: Product 1\nPrice: 10,00\nStatus: disabled", message);
    }
}