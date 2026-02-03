package com.enigmacamp.tokonyadia.spesification;

import com.enigmacamp.tokonyadia.dto.request.CustomerSearch;
import com.enigmacamp.tokonyadia.dto.request.TransactionSearch;
import com.enigmacamp.tokonyadia.entity.Customer;
import com.enigmacamp.tokonyadia.entity.Transaction;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TransactionSpecification {
    public static Specification<Transaction> getTransactionSpecification(TransactionSearch transactionSearch) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(transactionSearch.getDate() != null){
                Predicate transactionDatePredicate = criteriaBuilder.equal(root.get("date"), transactionSearch.getDate());
                predicates.add(transactionDatePredicate);
            }

            if (transactionSearch.getCustomer() != null && transactionSearch.getCustomer().getFullname() != null && !transactionSearch.getCustomer().getFullname().isBlank()) {
                Join<Transaction, Customer> customerJoin = root.join("customer", JoinType.INNER);
                Predicate customerNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(customerJoin.get("fullname")), "%" + transactionSearch.getCustomer().getFullname().toLowerCase() + "%");
                predicates.add(customerNamePredicate);
            }

            if (transactionSearch.getMinQuantity() != null){
                Predicate minQuantityPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("minQuantity"), transactionSearch.getMinQuantity());
                predicates.add(minQuantityPredicate);
            }

            if (transactionSearch.getMaxQuantity() != null){
                Predicate maxQuantityPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("maxQuantity"), transactionSearch.getMaxQuantity());
                predicates.add(maxQuantityPredicate);
            }

            if (transactionSearch.getMinPriceSell() != null){
                Predicate minPriceSellPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("minPriceSell"), transactionSearch.getMinPriceSell());
                predicates.add(minPriceSellPredicate);
            }

            if (transactionSearch.getMaxPriceSell() != null){
                Predicate maxPriceSellPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("maxPriceSell"), transactionSearch.getMaxPriceSell());
                predicates.add(maxPriceSellPredicate);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
