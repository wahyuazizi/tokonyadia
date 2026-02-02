package com.enigmacamp.tokonyadia.service;

import com.enigmacamp.tokonyadia.dto.request.ProductRequest;
import com.enigmacamp.tokonyadia.dto.request.ProductSearch;
import com.enigmacamp.tokonyadia.dto.response.ProductResponse;
import com.enigmacamp.tokonyadia.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product saveProduct(ProductRequest product);
    List<Product> saveAllProducts(List<Product> products);
    Page<ProductResponse> getAllProduct(Pageable pageable, ProductSearch productSearch);
    Product getProductById(UUID id);
    void deleteProduct(UUID id);
    Product updateProduct(ProductRequest product, UUID Id);
}
