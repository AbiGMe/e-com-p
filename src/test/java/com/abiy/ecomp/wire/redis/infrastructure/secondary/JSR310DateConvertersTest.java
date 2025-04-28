package com.abiy.ecomp.wire.redis.infrastructure.secondary;

import com.abiy.ecomp.UnitTest;
import com.abiy.ecomp.wire.redis.infrastructure.secondary.JSR310DateConverters.DateToZonedDateTimeConverter;
import com.abiy.ecomp.wire.redis.infrastructure.secondary.JSR310DateConverters.ZonedDateTimeToDateConverter;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@UnitTest
class JSR310DateConvertersTest {

    @Test
    void shouldConvertZoneDateTimeToDate() {
        var source = ZonedDateTime.parse("2022-02-15T12:00:00+01:00[Europe/Paris]");
        var expected = Date.from(source.toInstant());
        Date result = ZonedDateTimeToDateConverter.INSTANCE.convert(source);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldConvertDateToZoneDateTime() {
        ZonedDateTime expected = ZonedDateTime.parse("2022-02-15T12:00:00+01:00[Europe/Paris]");
        Date source = Date.from(expected.toInstant());
        ZonedDateTime result = DateToZonedDateTimeConverter.INSTANCE.convert(source);
        assertThat(result).isEqualTo(expected);
    }
}
