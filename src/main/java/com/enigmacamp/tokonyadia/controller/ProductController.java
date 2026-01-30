package com.enigmacamp.tokonyadia.controller;

import com.enigmacamp.tokonyadia.dto.request.ProductRequest;
import com.enigmacamp.tokonyadia.dto.response.ProductResponse;
import com.enigmacamp.tokonyadia.entity.Product;
import com.enigmacamp.tokonyadia.service.ProductService;
import com.enigmacamp.tokonyadia.utils.constant.ApiUrlConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiUrlConstant.PRODUCT)
public class ProductController {
    private final ProductService  productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productPayload) {

        Product product = productService.saveProduct(productPayload);
        return ResponseEntity.status(HttpStatus.CREATED).body(product.toResponse());
    }

    @PostMapping("/batch")
    public List<Product> saveAllProducts(@RequestBody List<Product> products) {
        return productService.saveAllProducts(products);
    }

    @GetMapping
    public Page<ProductResponse> getAllProduct(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "3") Integer size ) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.getAllProduct(pageable);
    }


    @GetMapping("/{id}")
    public ProductResponse  getProductById(@PathVariable UUID id) {
        return productService.getProductById(id).toResponse();
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable UUID id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable UUID id, @RequestBody ProductRequest product) {
        return productService.updateProduct(product, id);
    }
}
