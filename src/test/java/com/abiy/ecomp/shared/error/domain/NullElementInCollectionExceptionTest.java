package com.abiy.ecomp.shared.error.domain;

import com.abiy.ecomp.UnitTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@UnitTest
class NullElementInCollectionExceptionTest {

    @Test
    void shouldGetExceptionInformation() {
        var exception = new NullElementInCollectionException("myField");

        assertThat(exception.type()).isEqualTo(AssertionErrorType.NULL_ELEMENT_IN_COLLECTION);
        assertThat(exception.field()).isEqualTo("myField");
        assertThat(exception.parameters()).isEmpty();
        assertThat(exception.getMessage()).isEqualTo("The field \"myField\" contains a null element");
    }
}
