package com.samuca.fcarquiteturahexagonal.adapters.cli;

import com.samuca.fcarquiteturahexagonal.application.ProductInterface;
import com.samuca.fcarquiteturahexagonal.application.ProductServiceInterface;
import org.springframework.stereotype.Component;

@Component
public class ProductCli {

    private final ProductServiceInterface service;

    public ProductCli(ProductServiceInterface service) {
        this.service = service;
    }

    public String run(String action, String productId, String productName, double price) {
        ProductInterface product;
        String message;

        switch (action) {
            case "create":
                product = service.create(productName, price);
                message = "Product ID %s with the name %s has been created with the price %.2f and status %s";
                message = String.format(message, product.getId(), product.getName(), product.getPrice(), product.getStatus());
                break;

            case "enable":
                product = service.get(productId);
                product = service.enable(product);
                message = "Product %s has been enabled";
                message = String.format(message, product.getName());
                break;

            case "disable":
                product = service.get(productId);
                product = service.disable(product);
                message = "Product %s has been disabled";
                message = String.format(message, product.getName());
                break;

            default:
                product = service.get(productId);
                message = "Product ID: %s\nName: %s\nPrice: %.2f\nStatus: %s";
                message = String.format(message, product.getId(), product.getName(), product.getPrice(), product.getStatus());
                break;
        }

        return message;
    }
}
