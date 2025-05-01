package com.abiy.ecomp.feature.property.infrastructure.secondary.jpa.specs;

import com.abiy.ecomp.feature.property.domain.model.PropertySearchCriteria;
import com.abiy.ecomp.feature.property.infrastructure.secondary.model.Property;
import jakarta.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public record PropertySpec(PropertySearchCriteria criteria) implements Specification<Property> {
    @Override
    public Predicate toPredicate(Root<Property> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        // TODO: missing count

        query.distinct(true); // Prevents duplicate records when using fetch joins

        // **Solving N+1 Problem**: Fetch location & media items efficiently
        root.fetch("location", JoinType.LEFT);
        root.fetch("media", JoinType.LEFT);

        Predicate predicate = criteriaBuilder.conjunction();

        if (StringUtils.isNotBlank(criteria.title())) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("title"), "%" + criteria.title() + "%"));
        }

        if (criteria.minPrice() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("price"), criteria.minPrice()));
        }

        if (criteria.maxPrice() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("price"), criteria.maxPrice()));
        }

        if (criteria.bedrooms() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("bedrooms"), criteria.bedrooms()));
        }

        if (criteria.bathrooms() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("bathrooms"), criteria.bathrooms()));
        }

        if (criteria.available() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("available"), criteria.available()));
        }

        return predicate;
    }
}
