package com.abiy.ecomp.shared.pagination.domain;

import com.abiy.ecomp.shared.pagination.domain.EcompApplicationPage.EcompApplicationPageBuilder;

import java.util.List;

public final class EcompApplicationPagesFixture {

    private EcompApplicationPagesFixture() {
    }

    public static EcompApplicationPage<String> page() {
        return pageBuilder().build();
    }

    public static EcompApplicationPageBuilder<String> pageBuilder() {
        return EcompApplicationPage.builder(List.of("test")).currentPage(2).pageSize(10).totalElementsCount(21);
    }
}
