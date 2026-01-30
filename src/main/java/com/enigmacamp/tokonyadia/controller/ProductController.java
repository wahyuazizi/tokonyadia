package com.enigmacamp.tokonyadia.controller;

import com.enigmacamp.tokonyadia.entity.Product;
import com.enigmacamp.tokonyadia.service.ProductService;
import com.enigmacamp.tokonyadia.utils.constant.ApiUrlConstant;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiUrlConstant.PRODUCT)
public class ProductController {
    private final ProductService  productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PostMapping("/batch")
    public List<Product> saveAllProducts(@RequestBody List<Product> products) {
        return productService.saveAllProducts(products);
    }

    @GetMapping
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }


    @GetMapping("/{id}")
    public Product  getProductById(@RequestParam UUID id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable UUID id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable UUID id, @RequestBody Product product) {
        product.setId(id);
        return productService.updateProduct(product);
    }
}
