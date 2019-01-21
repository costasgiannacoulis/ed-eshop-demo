package org.acme.eshop.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {
	final static String CACHE_RESOLVER_NAME = "dynamicCacheResolver";

	@Bean
	@Override
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager();
	}

	@Bean(CACHE_RESOLVER_NAME)
	public CacheResolver cacheResolver(final CacheManager cacheManager) {
		return new DynamicCacheResolver(cacheManager);
	}

	@Override
	@Bean("CustomCacheKeyGenerator")
	public KeyGenerator keyGenerator() {
		return new CustomCacheKeyGenerator();
	}
}
