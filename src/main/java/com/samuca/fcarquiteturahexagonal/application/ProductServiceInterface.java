package com.samuca.fcarquiteturahexagonal.application;

public interface ProductServiceInterface {

    ProductInterface get(String id);
    ProductInterface create(String name, double price);
    ProductInterface enable(ProductInterface product);
    ProductInterface disable(ProductInterface product);
}
