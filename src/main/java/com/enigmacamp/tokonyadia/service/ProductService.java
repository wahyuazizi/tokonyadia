package com.enigmacamp.tokonyadia.service;

import com.enigmacamp.tokonyadia.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product> saveAllProducts(List<Product> products);
    List<Product> getAllProduct();
    Product getProductById(UUID id);
    void deleteProduct(UUID id);
    Product updateProduct(Product product);
}
