package com.abiy.ecomp.shared.pagination.domain;

import com.abiy.ecomp.UnitTest;
import com.abiy.ecomp.shared.error.domain.MissingMandatoryValueException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.abiy.ecomp.shared.pagination.domain.EcompApplicationPagesFixture.pageBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@UnitTest
class EcompApplicationPageTest {

    @Test
    void shouldGetEmptySinglePageWithoutContent() {
        EcompApplicationPage<String> page = EcompApplicationPage.singlePage(null);

        assertEmptyPage(page);
    }

    @Test
    void shouldGetEmptySinglePageFromBuilderWithoutContent() {
        EcompApplicationPage<?> page = EcompApplicationPage.builder(null).build();

        assertEmptyPage(page);
    }

    private void assertEmptyPage(EcompApplicationPage<?> page) {
        assertThat(page.content()).isEmpty();
        assertThat(page.currentPage()).isZero();
        assertThat(page.pageSize()).isZero();
        assertThat(page.totalElementsCount()).isZero();
    }

    @Test
    void shouldGetSinglePage() {
        EcompApplicationPage<String> page = EcompApplicationPage.singlePage(List.of("test", "dummy"));

        assertSinglePage(page);
    }

    @Test
    void shouldGetSinglePageFromBuilderWithContentOnly() {
        EcompApplicationPage<String> page = EcompApplicationPage.builder(List.of("test", "dummy")).build();

        assertSinglePage(page);
    }

    private void assertSinglePage(EcompApplicationPage<String> page) {
        assertThat(page.content()).containsExactly("test", "dummy");
        assertThat(page.currentPage()).isZero();
        assertThat(page.pageSize()).isEqualTo(2);
        assertThat(page.totalElementsCount()).isEqualTo(2);
        assertThat(page.pageCount()).isEqualTo(1);
    }

    @Test
    void shouldGetFullPage() {
        EcompApplicationPage<String> page = pageBuilder().build();

        assertThat(page.content()).containsExactly("test");
        assertThat(page.currentPage()).isEqualTo(2);
        assertThat(page.pageSize()).isEqualTo(10);
        assertThat(page.totalElementsCount()).isEqualTo(21);
        assertThat(page.pageCount()).isEqualTo(3);
    }

    @Test
    void shouldNotMapWithoutMapper() {
        assertThatThrownBy(() -> pageBuilder().build().map(null))
            .isExactlyInstanceOf(MissingMandatoryValueException.class)
            .hasMessageContaining("mapper");
    }

    @Test
    void shouldMapPage() {
        EcompApplicationPage<String> page = pageBuilder().build().map(entry -> "hey");

        assertThat(page.content()).containsExactly("hey");
        assertThat(page.currentPage()).isEqualTo(2);
        assertThat(page.pageSize()).isEqualTo(10);
        assertThat(page.totalElementsCount()).isEqualTo(21);
        assertThat(page.pageCount()).isEqualTo(3);
    }

    @Test
    void shouldNotBeLastForFirstPage() {
        assertThat(pageBuilder().currentPage(0).build().isNotLast()).isTrue();
    }

    @Test
    void shouldBeLastWithOnePage() {
        assertThat(EcompApplicationPage.singlePage(List.of("d")).isNotLast()).isFalse();
    }

    @Test
    void shouldBeLastPageWithoutContent() {
        EcompApplicationPage<Object> page = EcompApplicationPage.builder(List.of())
            .currentPage(0)
            .pageSize(1)
            .totalElementsCount(0)
            .build();
        assertThat(page.isNotLast()).isFalse();
    }

    @Test
    void shouldBeLastForLastPage() {
        assertThat(pageBuilder().currentPage(2).build().isNotLast()).isFalse();
    }

    @Test
    void shouldGetPageFromElements() {
        EcompApplicationPage<String> page = EcompApplicationPage.of(
            List.of("hello", "java", "world"),
            new EcompApplicationPageable(1, 1)
        );

        assertThat(page.currentPage()).isEqualTo(1);
        assertThat(page.hasNext()).isTrue();
        assertThat(page.hasPrevious()).isTrue();
        assertThat(page.pageCount()).isEqualTo(3);
        assertThat(page.pageSize()).isEqualTo(1);
        assertThat(page.content()).containsExactly("java");
    }

    @Test
    void shouldGetEmptyPageFromOutOfBoundElements() {
        EcompApplicationPage<String> page = EcompApplicationPage.of(
            List.of("hello", "java", "world"),
            new EcompApplicationPageable(4, 1)
        );

        assertThat(page.currentPage()).isEqualTo(4);
        assertThat(page.hasNext()).isFalse();
        assertThat(page.hasPrevious()).isTrue();
        assertThat(page.pageCount()).isEqualTo(3);
        assertThat(page.pageSize()).isEqualTo(1);
        assertThat(page.content()).isEmpty();
    }

    @Test
    void shouldGetPageWithLessThanExpectedElements() {
        EcompApplicationPage<String> page = EcompApplicationPage.of(
            List.of("hello", "java", "world"),
            new EcompApplicationPageable(0, 4)
        );

        assertThat(page.currentPage()).isZero();
        assertThat(page.hasNext()).isFalse();
        assertThat(page.hasPrevious()).isFalse();
        assertThat(page.pageCount()).isEqualTo(1);
        assertThat(page.pageSize()).isEqualTo(4);
        assertThat(page.content()).hasSize(3);
    }
}
