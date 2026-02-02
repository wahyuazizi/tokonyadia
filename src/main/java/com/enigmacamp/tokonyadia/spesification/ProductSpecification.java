package com.enigmacamp.tokonyadia.spesification;

import com.enigmacamp.tokonyadia.dto.request.ProductSearch;
import com.enigmacamp.tokonyadia.entity.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    public static Specification<Product> getSpecification(ProductSearch productSearch) {
        return(root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(productSearch.getProductName() != null && !productSearch.getProductName().isBlank()){
                Predicate productNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("productName")), "%" + productSearch.getProductName().toLowerCase() + "%");
                predicates.add(productNamePredicate);
            }

            if (productSearch.getMinPrice() != null ) {
                Predicate minPricePredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("productPrice"), productSearch.getMinPrice());
                predicates.add(minPricePredicate);
            }

            if (productSearch.getMaxPrice() != null ) {
                Predicate maxPricePredicate = criteriaBuilder.lessThanOrEqualTo(root.get("productPrice"), productSearch.getMaxPrice());
                predicates.add(maxPricePredicate);
            }
            if (productSearch.getStock() != null ) {
                Predicate stockPredicate = criteriaBuilder.equal(root.get("stock"), productSearch.getStock());
                predicates.add(stockPredicate);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
