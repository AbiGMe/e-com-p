package com.abiy.ecomp.shared.pagination.domain;

import com.abiy.ecomp.UnitTest;
import com.abiy.ecomp.shared.error.domain.NumberValueTooHighException;
import com.abiy.ecomp.shared.error.domain.NumberValueTooLowException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@UnitTest
class EcompApplicationPageableTest {

    @Test
    void shouldNotBuildWithNegativePage() {
        assertThatThrownBy(() -> new EcompApplicationPageable(-1, 10))
            .isExactlyInstanceOf(NumberValueTooLowException.class)
            .hasMessageContaining("page");
    }

    @Test
    void shouldNotBuildWithPageSizeAtZero() {
        assertThatThrownBy(() -> new EcompApplicationPageable(0, 0))
            .isExactlyInstanceOf(NumberValueTooLowException.class)
            .hasMessageContaining("pageSize");
    }

    @Test
    void shouldNotBuildWithPageSizeOverHundred() {
        assertThatThrownBy(() -> new EcompApplicationPageable(0, 101))
            .isExactlyInstanceOf(NumberValueTooHighException.class)
            .hasMessageContaining("pageSize");
    }

    @Test
    void shouldGetFirstPageInformation() {
        var pageable = new EcompApplicationPageable(0, 15);

        assertThat(pageable.page()).isZero();
        assertThat(pageable.pageSize()).isEqualTo(15);
        assertThat(pageable.offset()).isZero();
    }

    @Test
    void shouldGetPageableInformation() {
        var pageable = new EcompApplicationPageable(2, 15);

        assertThat(pageable.page()).isEqualTo(2);
        assertThat(pageable.pageSize()).isEqualTo(15);
        assertThat(pageable.offset()).isEqualTo(30);
    }
}
