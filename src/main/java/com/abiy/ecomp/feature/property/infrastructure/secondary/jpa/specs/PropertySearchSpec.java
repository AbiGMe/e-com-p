package com.abiy.ecomp.feature.property.infrastructure.secondary.jpa.specs;

import com.abiy.ecomp.feature.property.infrastructure.secondary.model.Property;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public record PropertySearchSpec(String searchTerm) implements Specification<Property> {
    @Override
    public Predicate toPredicate(Root<Property> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        query.distinct(true); // Prevents duplicate records from fetch joins

        // **Solving the N+1 Problem**: Efficiently fetch related entities
        root.fetch("location", JoinType.LEFT);
        root.fetch("media", JoinType.LEFT);

        Predicate predicate = criteriaBuilder.disjunction();

        // Concatenate city, state, country into a single searchable field
        Expression<String> fullLocation = criteriaBuilder.concat(criteriaBuilder.concat(root.get("location").get("city"), ", "),
                criteriaBuilder.concat(root.get("location").get("state"), ", ")
        );
        fullLocation = criteriaBuilder.concat(fullLocation, root.get("location").get("country"));

        // Apply search on multiple fields, including combined location
        predicate = criteriaBuilder.or(
                criteriaBuilder.like(root.get("title"), "%" + searchTerm + "%"),
                criteriaBuilder.like(root.get("description"), "%" + searchTerm + "%"),
                criteriaBuilder.like(root.get("propertyType").as(String.class), "%" + searchTerm + "%"),
                criteriaBuilder.like(fullLocation, "%" + searchTerm + "%") // Search city, state, country
        );

        return predicate;
    }
}
