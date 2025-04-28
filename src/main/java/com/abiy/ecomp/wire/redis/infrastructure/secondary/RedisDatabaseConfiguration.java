package com.abiy.ecomp.wire.redis.infrastructure.secondary;

import com.abiy.ecomp.wire.redis.infrastructure.secondary.JSR310DateConverters.DateToZonedDateTimeConverter;
import com.abiy.ecomp.wire.redis.infrastructure.secondary.JSR310DateConverters.ZonedDateTimeToDateConverter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.convert.RedisCustomConversions;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.util.List;

@Configuration
@EnableRedisRepositories({"com.abiy.ecomp"})
@Import(value = RedisAutoConfiguration.class)
class RedisDatabaseConfiguration {

    @Bean
    public RedisCustomConversions redisCustomConversions() {
        return new RedisCustomConversions(List.of(DateToZonedDateTimeConverter.INSTANCE, ZonedDateTimeToDateConverter.INSTANCE));
    }
}
