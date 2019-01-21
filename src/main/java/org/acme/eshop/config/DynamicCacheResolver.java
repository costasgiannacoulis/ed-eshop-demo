package org.acme.eshop.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.SimpleCacheResolver;

public class DynamicCacheResolver extends SimpleCacheResolver {
	protected DynamicCacheResolver(final CacheManager cacheManager) {
		super(cacheManager);
	}

	@Override
	protected Collection<String> getCacheNames(final CacheOperationInvocationContext<?> context) {
		return Arrays.asList(context.getTarget().getClass().getSimpleName());
	}
}
