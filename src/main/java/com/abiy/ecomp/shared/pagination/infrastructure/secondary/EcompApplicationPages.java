package com.abiy.ecomp.shared.pagination.infrastructure.secondary;

import com.abiy.ecomp.shared.error.domain.Assert;
import com.abiy.ecomp.shared.pagination.domain.EcompApplicationPage;
import com.abiy.ecomp.shared.pagination.domain.EcompApplicationPageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.function.Function;

public final class EcompApplicationPages {

    private EcompApplicationPages() {
    }

    public static Pageable from(EcompApplicationPageable pagination) {
        return from(pagination, Sort.unsorted());
    }

    public static Pageable from(EcompApplicationPageable pagination, Sort sort) {
        Assert.notNull("pagination", pagination);
        Assert.notNull("sort", sort);

        return PageRequest.of(pagination.page(), pagination.pageSize(), sort);
    }

    public static <S, T> EcompApplicationPage<T> from(Page<S> springPage, Function<S, T> mapper) {
        Assert.notNull("springPage", springPage);
        Assert.notNull("mapper", mapper);

        return EcompApplicationPage.builder(springPage.getContent().stream().map(mapper).toList())
            .currentPage(springPage.getNumber())
            .pageSize(springPage.getSize())
            .totalElementsCount(springPage.getTotalElements())
            .build();
    }
}
