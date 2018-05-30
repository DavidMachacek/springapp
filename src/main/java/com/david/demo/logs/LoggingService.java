package com.david.demo.logs;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.monitor.LocalMapStats;

@Aspect
@Component
public class LoggingService {

    private static final Logger log = LoggerFactory.getLogger(LoggingService.class);
    private final HazelcastInstance hazelcastInstance;

    public LoggingService(@Qualifier("hazelcastInstance") HazelcastInstance hazelcastInstance) {

        this.hazelcastInstance = hazelcastInstance;
    }

    @Before("@annotation(com.david.demo.logs.LogThis)")
    public void logCacheBefore() {
        logCache("=== cache stats BEFORE aspect ===");
    }

    @After("@annotation(com.david.demo.logs.LogThis)")
    public void logCacheAfter() {
        logCache("=== cache stats AFTER aspect ===");
    }

    private void logCache(String description) {
        LocalMapStats localMapStats = hazelcastInstance.getMap("customer-cache").getLocalMapStats();
        log.debug(description);
        log.debug("Backup count: {}", localMapStats.getBackupCount());
        log.debug("Hits count: {}", localMapStats.getHits());
    }
}
