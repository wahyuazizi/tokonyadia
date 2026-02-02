package com.enigmacamp.tokonyadia.spesification;

import com.enigmacamp.tokonyadia.dto.request.CustomerSearch;
import com.enigmacamp.tokonyadia.entity.Customer;
import com.enigmacamp.tokonyadia.utils.enums.Gender;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomerSpecification {
    public static Specification<Customer> getSpecification(CustomerSearch customerSearch){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(customerSearch.getFullname() != null && !customerSearch.getFullname().isBlank()){
                Predicate customerNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("fullname")), "%" + customerSearch.getFullname().toLowerCase() + "%");
                predicates.add(customerNamePredicate);
            }

            if(customerSearch.getEmail() != null && !customerSearch.getEmail().isBlank()){
                Predicate customerEmailPredicate = criteriaBuilder
                        .like(criteriaBuilder.lower(root.get("email")), "%" + customerSearch.getEmail().toLowerCase() + "%");
                predicates.add(customerEmailPredicate);
            }


            if (customerSearch.getGender() != null){
                Predicate customerGenderPredicate = criteriaBuilder.equal(root.get("gender"), customerSearch.getGender());
                predicates.add(customerGenderPredicate);
            }

            if(customerSearch.getAddress() != null && !customerSearch.getAddress().isBlank()){
                Predicate customerAddressPredicate = criteriaBuilder
                        .like(criteriaBuilder.lower(root.get("address")), "%" + customerSearch.getAddress().toLowerCase() + "%");
                predicates.add(customerAddressPredicate);
            }

            if(customerSearch.getMember() != null){
                Predicate customerMemberPredicate = criteriaBuilder.equal(root.get("member"), customerSearch.getMember());
                predicates.add(customerMemberPredicate);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
