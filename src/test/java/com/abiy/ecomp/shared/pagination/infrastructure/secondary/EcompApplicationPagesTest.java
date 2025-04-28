package com.abiy.ecomp.shared.pagination.infrastructure.secondary;

import com.abiy.ecomp.UnitTest;
import com.abiy.ecomp.shared.error.domain.MissingMandatoryValueException;
import com.abiy.ecomp.shared.pagination.domain.EcompApplicationPage;
import com.abiy.ecomp.shared.pagination.domain.EcompApplicationPageable;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@UnitTest
class EcompApplicationPagesTest {

    @Test
    void shouldNotBuildPageableFromNullEcompApplicationPageable() {
        assertThatThrownBy(() -> EcompApplicationPages.from(null))
            .isExactlyInstanceOf(MissingMandatoryValueException.class)
            .hasMessageContaining("pagination");
    }

    @Test
    void shouldBuildPageableFromEcompApplicationPageable() {
        Pageable pagination = EcompApplicationPages.from(pagination());

        assertThat(pagination.getPageNumber()).isEqualTo(2);
        assertThat(pagination.getPageSize()).isEqualTo(15);
        assertThat(pagination.getSort()).isEqualTo(Sort.unsorted());
    }

    @Test
    void shouldNotBuildWithoutSort() {
        assertThatThrownBy(() -> EcompApplicationPages.from(pagination(), null))
            .isExactlyInstanceOf(MissingMandatoryValueException.class)
            .hasMessageContaining("sort");
    }

    @Test
    void shouldBuildPageableFromEcompApplicationPageableAndSort() {
        Pageable pagination = EcompApplicationPages.from(pagination(), Sort.by("dummy"));

        assertThat(pagination.getPageNumber()).isEqualTo(2);
        assertThat(pagination.getPageSize()).isEqualTo(15);
        assertThat(pagination.getSort()).isEqualTo(Sort.by("dummy"));
    }

    private EcompApplicationPageable pagination() {
        return new EcompApplicationPageable(2, 15);
    }

    @Test
    void shouldNotConvertFromSpringPageWithoutSpringPage() {
        assertThatThrownBy(() -> EcompApplicationPages.from(null, source -> source))
            .isExactlyInstanceOf(MissingMandatoryValueException.class)
            .hasMessageContaining("springPage");
    }

    @Test
    void shouldNotConvertFromSpringPageWithoutMapper() {
        assertThatThrownBy(() -> EcompApplicationPages.from(springPage(), null))
            .isExactlyInstanceOf(MissingMandatoryValueException.class)
            .hasMessageContaining("mapper");
    }

    @Test
    void shouldConvertFromSpringPage() {
        EcompApplicationPage<String> page = EcompApplicationPages.from(springPage(), Function.identity());

        assertThat(page.content()).containsExactly("test");
        assertThat(page.currentPage()).isEqualTo(2);
        assertThat(page.pageSize()).isEqualTo(10);
        assertThat(page.totalElementsCount()).isEqualTo(30);
    }

    private PageImpl<String> springPage() {
        return new PageImpl<>(List.of("test"), PageRequest.of(2, 10), 30);
    }
}
