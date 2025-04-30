package com.abiy.ecomp.shared.mapper.infrastructure.domain;

import org.mapstruct.Mapper;

import java.time.*;

@Mapper(componentModel = "spring")
public interface CommonMapper {

    default Instant toInstant(LocalDate date) {
        return date.atTime(LocalTime.of(0, 0, 0, 0)).toInstant(ZoneOffset.UTC);
    }

    default OffsetDateTime toOffsetDateTime(LocalDateTime dateTime) {
        if (dateTime != null) {
            LocalDateTime localDateTime = LocalDateTime.now();
            ZoneOffset zoneOffSet = ZoneId.systemDefault().getRules().getOffset(localDateTime);
            return localDateTime.atOffset(zoneOffSet);
        }
        return null;
    }

    default LocalDateTime toOffsetDateTime(OffsetDateTime offsetDateTime) {
        if (offsetDateTime != null) {
            ZonedDateTime zoned = offsetDateTime.atZoneSameInstant(ZoneId.systemDefault());
            return zoned.toLocalDateTime();
        }
        return null;
    }

    default LocalDate toLocalDate(Instant instant) {
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
