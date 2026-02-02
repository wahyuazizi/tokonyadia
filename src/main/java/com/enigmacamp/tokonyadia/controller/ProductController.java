package com.enigmacamp.tokonyadia.controller;

import com.enigmacamp.tokonyadia.dto.request.ProductRequest;
import com.enigmacamp.tokonyadia.dto.request.ProductSearch;
import com.enigmacamp.tokonyadia.dto.response.PageResponseWrapper;
import com.enigmacamp.tokonyadia.dto.response.ProductResponse;
import com.enigmacamp.tokonyadia.entity.Product;
import com.enigmacamp.tokonyadia.service.ProductService;
import com.enigmacamp.tokonyadia.utils.constant.ApiUrlConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
//    Bisa langsung pake Pageable di paramnya
    // ?page=1&size=2&sort=propertisname,desc/ascc
    public ResponseEntity<PageResponseWrapper<ProductResponse>> getAllProduct(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "3") Integer size,
            @RequestParam(name = "sort", defaultValue = "productName") String sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortDirection,
            @ModelAttribute ProductSearch productSearch) {

        int validatePage = page > 0 ? page - 1 : 0;
        Pageable pageable = PageRequest.of(validatePage, size, Sort.by(sortDirection, sortBy));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new PageResponseWrapper<>(productService.getAllProduct(pageable, productSearch)));
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
