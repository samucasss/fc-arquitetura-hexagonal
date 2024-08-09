package com.samuca.fcarquiteturahexagonal.adapters.db;

import com.samuca.fcarquiteturahexagonal.application.Product;
import com.samuca.fcarquiteturahexagonal.application.ProductInterface;
import com.samuca.fcarquiteturahexagonal.application.ProductPersistenceInterface;
import org.springframework.stereotype.Repository;

@Repository
public class ProductPersistence implements ProductPersistenceInterface {

    private final ProductRepository productRepository;

    public ProductPersistence(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductInterface get(String id) {
        return productRepository.findById(id).get();
    }

    @Override
    public ProductInterface save(ProductInterface product) {
        return productRepository.save((Product) product);
    }
}
