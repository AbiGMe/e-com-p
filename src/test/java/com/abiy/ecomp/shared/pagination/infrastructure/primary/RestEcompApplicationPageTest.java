package com.abiy.ecomp.shared.pagination.infrastructure.primary;

import com.abiy.ecomp.JsonHelper;
import com.abiy.ecomp.UnitTest;
import com.abiy.ecomp.shared.error.domain.MissingMandatoryValueException;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static com.abiy.ecomp.shared.pagination.domain.EcompApplicationPagesFixture.page;
import static com.abiy.ecomp.shared.pagination.domain.EcompApplicationPagesFixture.pageBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@UnitTest
class RestEcompApplicationPageTest {

    @Test
    void shouldNotConvertWithoutSourcePage() {
        assertThatThrownBy(() -> RestEcompApplicationPage.from(null, source -> "test")).isExactlyInstanceOf(
            MissingMandatoryValueException.class
        );
    }

    @Test
    void shouldNotConvertWithoutMappingFunction() {
        assertThatThrownBy(() -> RestEcompApplicationPage.from(page(), null)).isExactlyInstanceOf(
            MissingMandatoryValueException.class
        );
    }

    @Test
    void shouldMapFromDomainPage() {
        RestEcompApplicationPage<String> page = RestEcompApplicationPage.from(page(), Function.identity());

        assertThat(page.getContent()).containsExactly("test");
        assertThat(page.getCurrentPage()).isEqualTo(2);
        assertThat(page.getPageSize()).isEqualTo(10);
        assertThat(page.getTotalElementsCount()).isEqualTo(21);
        assertThat(page.getPagesCount()).isEqualTo(3);
    }

    @Test
    void shouldGetPageCountForPageLimit() {
        RestEcompApplicationPage<String> page = RestEcompApplicationPage.from(
            pageBuilder().totalElementsCount(3).pageSize(3).build(),
            Function.identity()
        );

        assertThat(page.getPagesCount()).isEqualTo(1);
    }

    @Test
    void shouldSerializeToJson() {
        assertThat(JsonHelper.writeAsString(RestEcompApplicationPage.from(page(), Function.identity()))).isEqualTo(json());
    }

    private String json() {
        return """
            {"content":["test"],\
            "currentPage":2,\
            "pageSize":10,\
            "totalElementsCount":21,\
            "pagesCount":3,\
            "hasPrevious":true,\
            "hasNext":false\
            }\
            """;
    }
}
