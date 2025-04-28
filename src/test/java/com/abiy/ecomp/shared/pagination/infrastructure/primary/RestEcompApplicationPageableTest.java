package com.abiy.ecomp.shared.pagination.infrastructure.primary;

import com.abiy.ecomp.UnitTest;
import com.abiy.ecomp.shared.pagination.domain.EcompApplicationPageable;
import org.junit.jupiter.api.Test;

import static com.abiy.ecomp.BeanValidationAssertions.assertThatBean;
import static org.assertj.core.api.Assertions.assertThat;

@UnitTest
class RestEcompApplicationPageableTest {

    @Test
    void shouldConvertToDomain() {
        EcompApplicationPageable pageable = pageable().toPageable();

        assertThat(pageable.page()).isEqualTo(1);
        assertThat(pageable.pageSize()).isEqualTo(15);
    }

    @Test
    void shouldNotValidateWithPageUnderZero() {
        RestEcompApplicationPageable pageable = pageable();
        pageable.setPage(-1);

        assertThatBean(pageable).hasInvalidProperty("page");
    }

    @Test
    void shouldNotValidateWithSizeAtZero() {
        RestEcompApplicationPageable pageable = pageable();
        pageable.setPageSize(0);

        assertThatBean(pageable).hasInvalidProperty("pageSize").withParameter("value", 1L);
    }

    @Test
    void shouldNotValidateWithPageSizeOverHundred() {
        RestEcompApplicationPageable pageable = pageable();
        pageable.setPageSize(101);

        assertThatBean(pageable).hasInvalidProperty("pageSize");
    }

    private RestEcompApplicationPageable pageable() {
        return new RestEcompApplicationPageable(1, 15);
    }
}
