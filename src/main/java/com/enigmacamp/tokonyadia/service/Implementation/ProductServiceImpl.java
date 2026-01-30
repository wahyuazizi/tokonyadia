package com.enigmacamp.tokonyadia.service.Implementation;

import com.enigmacamp.tokonyadia.entity.Product;
import com.enigmacamp.tokonyadia.repository.ProductRepository;
import com.enigmacamp.tokonyadia.service.ProductService;
import org.springframework.data.crossstore.ChangeSetPersister;
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
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> saveAllProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(UUID id) {
        try {
            if(!productRepository.existsById(id)) {
                throw new ChangeSetPersister.NotFoundException();
            }
            return productRepository.getReferenceById(id);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteProduct(UUID id) {
        getProductById(id);
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Product product) {
        getProductById(product.getId());
        return saveProduct(product);
    }
}
