package com.samuca.fcarquiteturahexagonal.application;

public interface ProductInterface {

    boolean isValid();
    void enable();
    void disable();
    String getId();
    String getName();
    String getStatus();
    double getPrice();
    void changePrice(double price);
}
