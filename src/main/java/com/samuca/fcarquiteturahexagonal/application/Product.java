package com.samuca.fcarquiteturahexagonal.application;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product implements ProductInterface {

    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private String status = StatusEnum.DISABLED.getStatus();
    private double price = 0;


    public Product(String name) {
        this.name = name;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean isValid() {
        if (!StringUtils.hasText(status)) {
            status = StatusEnum.DISABLED.getStatus();
        }

        if (!List.of(StatusEnum.ENABLED.getStatus(), StatusEnum.DISABLED.getStatus()).contains(status)) {
            throw new RuntimeException("the status must be enabled or disabled");
        }

        if (price < 0) {
            throw new RuntimeException("the price must be greater than or equal to zero");
        }

        if (!StringUtils.hasText(id) || !StringUtils.hasText(name) || !StringUtils.hasText(status)) {
            return false;
        }

        return true;
    }

    @Override
    public void enable() {
        if (price <= 0) {
            throw new RuntimeException("the price must be greater than zero to enable the product");
        }

        status = StatusEnum.ENABLED.getStatus();
    }

    @Override
    public void disable() {
        if (price > 0) {
            throw new RuntimeException("the price must be zero to disable the product");
        }

        status = StatusEnum.DISABLED.getStatus();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void changePrice(double price) {
        if (price < 0) {
            throw new RuntimeException("the price must be greater than or equal to zero");
        }

        this.price = price;
    }
}
