package com.samuca.fcarquiteturahexagonal.adapters.web;

import com.samuca.fcarquiteturahexagonal.adapters.web.dto.ProductRequest;
import com.samuca.fcarquiteturahexagonal.adapters.web.dto.ProductResponse;
import com.samuca.fcarquiteturahexagonal.application.Product;
import com.samuca.fcarquiteturahexagonal.application.ProductInterface;
import com.samuca.fcarquiteturahexagonal.application.ProductServiceInterface;
import com.samuca.fcarquiteturahexagonal.application.StatusEnum;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ProductRestControllerTest {

    @Test
    void get() {
        ProductInterface product = new Product("1", "Product 1", StatusEnum.DISABLED.getStatus(), 100.0);
        ProductServiceInterface service = Mockito.mock(ProductServiceInterface.class);
        Mockito.when(service.get("1")).thenReturn(product);

        ProductRestController controller = new ProductRestController(service);
        ProductResponse response = controller.get("1");

        assertEquals("1", response.id());
        assertEquals("Product 1", response.name());
        assertEquals(StatusEnum.DISABLED.getStatus(), response.status());
        assertEquals(100.0, response.price());
    }

    @Test
    void create() {
        ProductInterface product = new Product("1", "Product 1", StatusEnum.DISABLED.getStatus(), 100.0);
        ProductServiceInterface service = Mockito.mock(ProductServiceInterface.class);
        Mockito.when(service.create("Product 1", 100.0)).thenReturn(product);

        ProductRestController controller = new ProductRestController(service);
        ProductResponse response = controller.create(new ProductRequest("Product 1", 100.0));

        assertEquals("1", response.id());
        assertEquals("Product 1", response.name());
        assertEquals(StatusEnum.DISABLED.getStatus(), response.status());
        assertEquals(100.0, response.price());
    }

    @Test
    void enable() {
        ProductInterface product = new Product("1", "Product 1", StatusEnum.DISABLED.getStatus(), 100.0);
        ProductServiceInterface service = Mockito.mock(ProductServiceInterface.class);
        Mockito.when(service.get("1")).thenReturn(product);
        Mockito.when(service.enable(product)).thenReturn(new Product("1", "Product 1", StatusEnum.ENABLED.getStatus(), 100.0));

        ProductRestController controller = new ProductRestController(service);
        ProductResponse response = controller.enable("1");

        assertEquals("1", response.id());
        assertEquals("Product 1", response.name());
        assertEquals(StatusEnum.ENABLED.getStatus(), response.status());
        assertEquals(100.0, response.price());
    }

    @Test
    void disable() {
        ProductInterface product = new Product("1", "Product 1", StatusEnum.ENABLED.getStatus(), 0.0);
        ProductServiceInterface service = Mockito.mock(ProductServiceInterface.class);
        Mockito.when(service.get("1")).thenReturn(product);
        Mockito.when(service.disable(product)).thenReturn(new Product("1", "Product 1", StatusEnum.DISABLED.getStatus(), 0.0));

        ProductRestController controller = new ProductRestController(service);
        ProductResponse response = controller.disable("1");

        assertEquals("1", response.id());
        assertEquals("Product 1", response.name());
        assertEquals(StatusEnum.DISABLED.getStatus(), response.status());
        assertEquals(0.0, response.price());
    }
}