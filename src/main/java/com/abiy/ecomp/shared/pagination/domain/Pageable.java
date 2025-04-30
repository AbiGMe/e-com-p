package com.abiy.ecomp.shared.pagination.domain;

import com.abiy.ecomp.shared.error.domain.Assert;
import com.abiy.ecomp.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Pageable {

    private int page;
    private int pageSize;
    private int offset;
    private String sortBy;
    private Direction direction;

    public Pageable(int page, int pageSize, String sortBy, Direction direction) {
        Assert.field("page", page).min(0);
        Assert.field("pageSize", pageSize).min(1).max(100);

        this.sortBy = sortBy;
        this.direction = direction;
        this.page = page;
        this.pageSize = pageSize;
        offset = page * pageSize;
    }

    public Pageable() {
    }

    @Override
    @ExcludeFromGeneratedCodeCoverage
    public int hashCode() {
        return new HashCodeBuilder().append(page).append(pageSize).build();
    }

    @Override
    @ExcludeFromGeneratedCodeCoverage
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Pageable other = (Pageable) obj;
        return new EqualsBuilder().append(page, other.page).append(pageSize, other.pageSize).build();
    }

    public int getPage() {
        return page;
    }

    public Pageable page(int page) {
        this.page = page;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Pageable pageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getOffset() {
        return offset;
    }

    public Pageable offset(int offset) {
        this.offset = offset;
        return this;
    }

    public String getSortBy() {
        return sortBy;
    }

    public Pageable sortBy(String sortBy) {
        this.sortBy = sortBy;
        return this;
    }

    public Direction getDirection() {
        return direction;
    }

    public Pageable direction(Direction direction) {
        this.direction = direction;
        return this;
    }

    public enum Direction {
        ASC, DESC
    }
}
