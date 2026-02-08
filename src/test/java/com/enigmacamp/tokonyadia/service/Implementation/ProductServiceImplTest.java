package com.enigmacamp.tokonyadia.service.Implementation;

import com.enigmacamp.tokonyadia.dto.request.ProductRequest;
import com.enigmacamp.tokonyadia.dto.request.ProductSearch;
import com.enigmacamp.tokonyadia.dto.response.ProductResponse;
import com.enigmacamp.tokonyadia.entity.Product;
import com.enigmacamp.tokonyadia.repository.ProductRepository;
import com.enigmacamp.tokonyadia.utils.constant.ResponseMessage;
import com.enigmacamp.tokonyadia.utils.exception.DataNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

//    GIVEN
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private UUID productId;
    private ProductRequest productRequest;
    private Product product;
    private Product savedProduct;

    @BeforeEach
    void setUp() {
        productId = UUID.randomUUID();
        productRequest = new ProductRequest(
                "Laptop",
                20000000D,
                9
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
                .productPrice(20000000D)
                .stock(9)
                .build();
    }

    @Test
    void saveProduct_shouldReturnSavedProduct() {
//        GIVEN
        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

//        WHEN
        Product result = productService.saveProduct(productRequest);

//        THEN
        assertNotNull(result);
        assertEquals(savedProduct.getProductName(), result.getProductName());
        assertThat(result.getProductPrice()).isEqualTo(20000000.00);
        assertThat(result.getStock()).isEqualTo(9);

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void saveAllProducts() {
    }

    @Test
    void getAllProduct_ShouldReturnPageOfProductResponse() {
//        Given
        ProductSearch productSearch = new ProductSearch();
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(List.of(product), pageable, 1);

        when(productRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(productPage);

        Page<ProductResponse> result = productService.getAllProduct(pageable, productSearch);

        assertNotNull(result);
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().getFirst().getName()).isEqualTo("Laptop");

        verify(productRepository).findAll(any(Specification.class), eq(pageable));

    }

    @Test
    void getProductById_WhenProductExists_ShouldReturnProduct() {
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(productId);

        assertNotNull(result);
        assertThat(result.getId()).isEqualTo(productId);
        assertThat(result.getProductName()).isEqualTo("Laptop");
        assertThat(result.getProductPrice()).isEqualTo(20000000.00);
        assertThat(result.getStock()).isEqualTo(9);
    }

    @Test
    void getProductById_WhenProductDoesNotExist_ShouldThrowException() {
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThatThrownBy(()->productService.getProductById(productId))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessageContaining("product");

        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void deleteProduct_WhenProductExists_ShouldDeleteProduct() {
//        GIVEN
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).deleteById(productId);

//        WHEN
        productService.deleteProduct(productId);

//        THEN
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).deleteById(productId);
        assertTrue(product.getDeleted());
        assertNotNull(product.getDeletedAt());
    }

    @Test
    void deleteProduct_WhenProductNotExists_ShouldThrowException(){
//        Given
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

//        When
        DataNotFoundException exception = assertThrows(
                DataNotFoundException.class, () -> productService.getProductById(productId)
        );

        assertTrue(exception.getMessage().contains(ResponseMessage.PRODUCT));
        assertTrue(exception.getMessage().contains(productId.toString()));
        verify(productRepository, times(1)).findById(productId);

    }

    @Test
    void updateProduct_WhenProductExists_ShouldReturnUpdatedProduct() {
//        GIVEN
        ProductRequest updateRequest = new ProductRequest(
                "Laptop Gaming",
                25_000_000D,
                15
        );

        Product updatedProduct = Product.builder()
                .id(productId)
                .productName("Laptop Gaming")
                .productPrice(25_000_000D)
                .stock(15)
                .build();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

//        WHEN
        Product result = productService.updateProduct(updateRequest, productId);

//        THEN
        assertNotNull(result);
        assertEquals(productId, result.getId());
        assertEquals(updateRequest.productName(), result.getProductName());
        assertEquals(updateRequest.productPrice(), result.getProductPrice());
        assertEquals(updateRequest.stock(), result.getStock());
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void updateProduct_WhenProductNotExists_ShouldThrowException(){
//        GIVEN
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

//        WHEN and THEN
//        assertThrows(
//                DataNotFoundException.class, ()-> productService.updateProduct(productRequest, productId)
//        );
        assertThatThrownBy(()->productService.updateProduct(productRequest, productId))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessageContaining("product")
                .hasMessageContaining(productId.toString());

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, never()).save(any());
    }
}