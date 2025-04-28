package com.abiy.ecomp.shared.pagination.infrastructure.primary;

import com.abiy.ecomp.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;
import com.abiy.ecomp.shared.pagination.domain.EcompApplicationPageable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Schema(name = "EcompApplicationPageable", description = "Pagination information")
public class RestEcompApplicationPageable {

    private int page;
    private int pageSize = 10;

    @ExcludeFromGeneratedCodeCoverage
    public RestEcompApplicationPageable() {
    }

    public RestEcompApplicationPageable(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    @Min(value = 0)
    @Schema(description = "Page to display (starts at 0)", example = "0")
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Min(value = 1)
    @Max(value = 100)
    @Schema(description = "Number of elements on each page", example = "10")
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public EcompApplicationPageable toPageable() {
        return new EcompApplicationPageable(page, pageSize);
    }
}
