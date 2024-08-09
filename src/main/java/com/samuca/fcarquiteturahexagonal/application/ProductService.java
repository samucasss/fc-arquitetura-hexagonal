package com.samuca.fcarquiteturahexagonal.application;

import org.springframework.stereotype.Service;

@Service
public class ProductService implements ProductServiceInterface {

    private final ProductPersistenceInterface persistence;

    public ProductService(ProductPersistenceInterface persistence) {
        this.persistence = persistence;
    }

    @Override
    public ProductInterface get(String id) {
        return persistence.get(id);
    }

    @Override
    public ProductInterface create(String name, double price) {
        ProductInterface product = new Product(name, price);
        if (product.isValid()) {
            return persistence.save(product);
        }

        return null;
    }

    @Override
    public ProductInterface enable(ProductInterface product) {
        product.enable();
        return persistence.save(product);
    }

    @Override
    public ProductInterface disable(ProductInterface product) {
        product.disable();
        return persistence.save(product);
    }
}
