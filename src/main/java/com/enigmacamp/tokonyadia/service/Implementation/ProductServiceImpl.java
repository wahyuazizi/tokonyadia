package com.enigmacamp.tokonyadia.service.Implementation;

import com.enigmacamp.tokonyadia.dto.request.ProductRequest;
import com.enigmacamp.tokonyadia.dto.request.ProductSearch;
import com.enigmacamp.tokonyadia.dto.response.ProductResponse;
import com.enigmacamp.tokonyadia.entity.Product;
import com.enigmacamp.tokonyadia.repository.ProductRepository;
import com.enigmacamp.tokonyadia.service.ProductService;
import com.enigmacamp.tokonyadia.spesification.ProductSpecification;
import com.enigmacamp.tokonyadia.utils.constant.ResponseMessage;
import com.enigmacamp.tokonyadia.utils.exception.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(ProductRequest productPayload) {
        Product product = Product.builder()
                .productName(productPayload.productName())
                .productPrice(productPayload.productPrice())
                .stock(productPayload.stock())
                .build();
        return productRepository.save(product);
    }

    @Override
    public List<Product> saveAllProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public Page<ProductResponse> getAllProduct(Pageable pageable, ProductSearch productSearch) {
        Specification<Product> productSpecification = ProductSpecification.getSpecification(productSearch);

        return productRepository.findAll(productSpecification, pageable)
                .map(Product::toResponse);
    }

    @Override
    public Product getProductById(UUID id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new DataNotFoundException(
                String.format(
                    ResponseMessage.NOT_FOUND_MESSAGE,
                    ResponseMessage.PRODUCT,
                    id
                )
            )
        );
    }


    @Override
    public void deleteProduct(UUID id) {
        getProductById(id);
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(ProductRequest productPayload, UUID id) {
        getProductById(id);
        Product product = Product.builder()
                .id(id)
                .productName(productPayload.productName())
                .productPrice(productPayload.productPrice())
                .stock(productPayload.stock())
                .build();
        return productRepository.save(product);
    }
}
