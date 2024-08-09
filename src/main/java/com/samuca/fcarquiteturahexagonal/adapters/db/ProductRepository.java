package com.samuca.fcarquiteturahexagonal.adapters.db;

import com.samuca.fcarquiteturahexagonal.application.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
