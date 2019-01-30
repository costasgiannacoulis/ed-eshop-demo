package org.acme.eshop.schedule;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CacheStatistics {
	private final CacheManager cacheManager;

	@Scheduled(cron = "0 * * * * *")
	public void defaultCacheStatistics() {
		log.debug("--------------------------------------------------------");
		log.debug("Displaying cache names and sizes.");

		final AtomicInteger index = new AtomicInteger(1);

		cacheManager.getCacheNames().stream().sorted().map(name -> cacheManager.getCache(name)).
			forEach(cache -> log.debug("{}. Cache \"{}\" has the size of {}.", index.getAndIncrement(),
									   cache.getName(),
									   ((ConcurrentHashMap) cache.getNativeCache()).size()));
		log.debug("--------------------------------------------------------");
	}
}
