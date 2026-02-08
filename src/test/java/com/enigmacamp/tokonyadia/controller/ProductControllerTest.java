package com.enigmacamp.tokonyadia.controller;

import com.enigmacamp.tokonyadia.dto.request.ProductRequest;
import com.enigmacamp.tokonyadia.entity.Product;
import com.enigmacamp.tokonyadia.security.jwt.JwtFilter;
import com.enigmacamp.tokonyadia.security.jwt.JwtUtil;
import com.enigmacamp.tokonyadia.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {

    @MockitoBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private JwtUtil jwtUtil;
    @MockitoBean
    private JwtFilter jwtFilter;

    private ProductRequest productRequest;
    private Product savedProduct;
    private Product product;

    @BeforeEach
    void setUp(){
        UUID productId = UUID.randomUUID();
        productRequest = new ProductRequest(
                "Laptop", 20000000D, 9
        );
        savedProduct = Product.builder()
                .id(productId)
                .productName("Laptop")
                .productPrice(20000000D)
                .stock(9)
                .build();

        product = Product.builder()
                .id(productId)
                .productName("Laptop")
                .productPrice(20_000_000D)
                .stock(9)
                .build();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createProduct_shouldReturnCreatedProduct() throws Exception {
        when(productService.saveProduct(Mockito.any()))
                .thenReturn(savedProduct);

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name")
                        .value("Laptop")
        );

    }

    @Test
    void saveAllProducts() {
    }

    @Test
    void getAllProduct() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void deleteProductById() {
    }

    @Test
    void updateProduct() {
    }
}