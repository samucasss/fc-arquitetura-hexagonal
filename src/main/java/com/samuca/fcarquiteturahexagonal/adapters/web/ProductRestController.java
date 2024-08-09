package com.samuca.fcarquiteturahexagonal.adapters.web;

import com.samuca.fcarquiteturahexagonal.adapters.web.dto.ProductRequest;
import com.samuca.fcarquiteturahexagonal.adapters.web.dto.ProductResponse;
import com.samuca.fcarquiteturahexagonal.application.ProductInterface;
import com.samuca.fcarquiteturahexagonal.application.ProductServiceInterface;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductRestController {

    private final ProductServiceInterface service;

    public ProductRestController(ProductServiceInterface service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello web!";
    }

    @GetMapping("/products/{id}")
    public ProductResponse get(@PathVariable String id) {
        ProductInterface product = service.get(id);
        return new ProductResponse(product.getId(), product.getName(), product.getStatus(), product.getPrice());
    }

    @PostMapping("/products")
    public ProductResponse create(@RequestBody ProductRequest request) {
        ProductInterface product = service.create(request.name(), request.price());
        return new ProductResponse(product.getId(), product.getName(), product.getStatus(), product.getPrice());
    }

    @PutMapping("/products/{id}/enable")
    public ProductResponse enable(@PathVariable String id) {
        ProductInterface product = service.get(id);
        product = service.enable(product);
        return new ProductResponse(product.getId(), product.getName(), product.getStatus(), product.getPrice());
    }

    @PutMapping("/products/{id}/disable")
    public ProductResponse disable(@PathVariable String id) {
        ProductInterface product = service.get(id);
        product = service.disable(product);
        return new ProductResponse(product.getId(), product.getName(), product.getStatus(), product.getPrice());
    }

}
