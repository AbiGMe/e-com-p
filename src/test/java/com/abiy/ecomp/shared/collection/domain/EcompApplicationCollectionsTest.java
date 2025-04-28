package com.abiy.ecomp.shared.collection.domain;

import com.abiy.ecomp.UnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@UnitTest
class EcompApplicationCollectionsTest {

    @Nested
    @DisplayName("Collections")
    class EcompApplicationCollectionsCollectionsTest {

        @Test
        void shouldGetEmptyImmutableCollectionFromNullCollection() {
            Collection<Object> input = null;
            Collection<Object> collection = EcompApplicationCollections.immutable(input);

            assertThat(collection).isEmpty();
            assertThatThrownBy(collection::clear).isExactlyInstanceOf(UnsupportedOperationException.class);
        }

        @Test
        void shouldGetImmutableCollectionFromMutableCollection() {
            Collection<String> input = new ArrayList<>();
            input.add("value");
            Collection<String> collection = EcompApplicationCollections.immutable(input);

            assertThat(collection).containsExactly("value");
            assertThatThrownBy(collection::clear).isExactlyInstanceOf(UnsupportedOperationException.class);
        }
    }

    @Nested
    @DisplayName("Set")
    class EcompApplicationCollectionsSetTest {

        @Test
        void shouldGetEmptyImmutableCollectionFromNullCollection() {
            Set<Object> input = null;
            Set<Object> set = EcompApplicationCollections.immutable(input);

            assertThat(set).isEmpty();
            assertThatThrownBy(set::clear).isExactlyInstanceOf(UnsupportedOperationException.class);
        }

        @Test
        void shouldGetImmutableCollectionFromMutableCollection() {
            Set<String> input = new HashSet<>();
            input.add("value");
            Set<String> set = EcompApplicationCollections.immutable(input);

            assertThat(set).containsExactly("value");
            assertThatThrownBy(set::clear).isExactlyInstanceOf(UnsupportedOperationException.class);
        }
    }

    @Nested
    @DisplayName("List")
    class EcompApplicationCollectionsListTest {

        @Test
        void shouldGetEmptyImmutableCollectionFromNullCollection() {
            List<Object> input = null;
            List<Object> list = EcompApplicationCollections.immutable(input);

            assertThat(list).isEmpty();
            assertThatThrownBy(list::clear).isExactlyInstanceOf(UnsupportedOperationException.class);
        }

        @Test
        void shouldGetImmutableCollectionFromMutableCollection() {
            List<String> input = new ArrayList<>();
            input.add("value");
            List<String> list = EcompApplicationCollections.immutable(input);

            assertThat(list).containsExactly("value");
            assertThatThrownBy(list::clear).isExactlyInstanceOf(UnsupportedOperationException.class);
        }
    }

    @Nested
    @DisplayName("Map")
    class EcompApplicationMapTest {

        @Test
        void shouldGetEmptyImmutableMapFromNullMap() {
            Map<Object, Object> input = null;
            Map<Object, Object> map = EcompApplicationCollections.immutable(input);

            assertThat(map).isEmpty();
            assertThatThrownBy(map::clear).isExactlyInstanceOf(UnsupportedOperationException.class);
        }

        @Test
        void shouldGetImmutableMapFromMutableMap() {
            Map<String, String> input = new HashMap<>();
            input.put("key", "value");
            Map<String, String> map = EcompApplicationCollections.immutable(input);

            assertThat(map).containsExactly(Map.entry("key", "value"));
            assertThatThrownBy(map::clear).isExactlyInstanceOf(UnsupportedOperationException.class);
        }
    }
}
