    package com.samuca.fcarquiteturahexagonal.adapters.web;

import com.samuca.fcarquiteturahexagonal.adapters.db.ProductRepository;
import com.samuca.fcarquiteturahexagonal.adapters.web.dto.ProductRequest;
import com.samuca.fcarquiteturahexagonal.adapters.web.dto.ProductResponse;
import com.samuca.fcarquiteturahexagonal.application.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductRestControllerIntegrationTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository repository;

    @Test
    void get() {
        Product product = new Product("Product 1", 100.0);
        repository.save(product);

        String url = "http://localhost:" + port + "/products/" + product.getId();

        ProductResponse response = restTemplate.getForObject(url, ProductResponse.class);

        assertNotNull(response.id());
        assertEquals("Product 1", response.name());
        assertEquals(StatusEnum.DISABLED.getStatus(), response.status());
        assertEquals(100.0, response.price());
    }

    @Test
    void create() {
        String url = "http://localhost:" + port + "/products";

        ProductRequest request = new ProductRequest("Product 1", 100.0);
        ProductResponse response = restTemplate.postForObject(url, request, ProductResponse.class);

        assertNotNull(response.id());
        assertEquals("Product 1", response.name());
        assertEquals(StatusEnum.DISABLED.getStatus(), response.status());
        assertEquals(100.0, response.price());
    }

    @Test
    void enable() {
        Product product = new Product("Product 1", 100.0);
        repository.save(product);

        String url = "http://localhost:" + port + "/products/" + product.getId() + "/enable";

        ProductResponse response = restTemplate.exchange(url, org.springframework.http.HttpMethod.PUT, null,
                ProductResponse.class).getBody();

        assertNotNull(response.id());
        assertEquals("Product 1", response.name());
        assertEquals(StatusEnum.ENABLED.getStatus(), response.status());
        assertEquals(100.0, response.price());
    }

    @Test
    void disable() {
        Product product = new Product("Product 1", 0.0);
        repository.save(product);

        String url = "http://localhost:" + port + "/products/" + product.getId() + "/disable";

        ProductResponse response = restTemplate.exchange(url, org.springframework.http.HttpMethod.PUT, null,
                ProductResponse.class).getBody();

        assertNotNull(response.id());
        assertEquals("Product 1", response.name());
        assertEquals(StatusEnum.DISABLED.getStatus(), response.status());
        assertEquals(0.0, response.price());
    }

}
