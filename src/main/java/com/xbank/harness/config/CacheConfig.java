package com.xbank.harness.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
    // Basic setup enabling caching. In a true distributed scenario with Redis, 
    // Spring Boot automatically configures the RedisCacheManager based on the spring.data.redis properties.
}
